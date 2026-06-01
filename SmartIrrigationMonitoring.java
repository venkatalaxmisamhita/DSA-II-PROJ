class SegmentTree {

    double[] tree;
    double[] lazy;
    int n;

    SegmentTree(double[] arr) {
        n = arr.length;
        tree = new double[4 * n];
        lazy = new double[4 * n];
        build(arr, 0, 0, n - 1);
    }

    void build(double[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;

            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);

            tree[node] = Math.max(tree[2 * node + 1],
                                  tree[2 * node + 2]);
        }
    }

    void propagate(int node, int start, int end) {

        if (lazy[node] != 0) {

            tree[node] += lazy[node];

            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }

            lazy[node] = 0;
        }
    }

    void update(int node, int start, int end,
                int l, int r, double val) {

        propagate(node, start, end);

        if (start > r || end < l)
            return;

        if (l <= start && end <= r) {

            tree[node] += val;

            if (start != end) {
                lazy[2 * node + 1] += val;
                lazy[2 * node + 2] += val;
            }

            return;
        }

        int mid = (start + end) / 2;

        update(2 * node + 1, start, mid, l, r, val);
        update(2 * node + 2, mid + 1, end, l, r, val);

        tree[node] = Math.max(tree[2 * node + 1],
                              tree[2 * node + 2]);
    }

    double query(int node, int start, int end,
                 int l, int r) {

        propagate(node, start, end);

        if (start > r || end < l)
            return Double.MIN_VALUE;

        if (l <= start && end <= r)
            return tree[node];

        int mid = (start + end) / 2;

        double left = query(2 * node + 1,
                            start, mid, l, r);

        double right = query(2 * node + 2,
                             mid + 1, end, l, r);

        return Math.max(left, right);
    }
}

public class SmartIrrigationMonitoring {

    public static void main(String[] args) {

        int n = 16;

        double[] zones = new double[n];

        for (int i = 0; i < n; i++) {
            zones[i] = 1.0;
        }

        SegmentTree st = new SegmentTree(zones);

        System.out.println("Initial Water Levels:");

        for (double val : zones) {
            System.out.print(val + " ");
        }

        System.out.println();

        // Operation 1
        st.update(0, 0, n - 1, 3, 9, 0.5);

        // Operation 2
        st.update(0, 0, n - 1, 7, 14, 0.3);

        // Query 1
        double ans1 = st.query(0, 0, n - 1, 0, 15);

        System.out.println("\nMaximum Water Level in Zones [0,15] : " + ans1);

        // Operation 4
        st.update(0, 0, n - 1, 2, 6, 0.7);

        // Query 2
        double ans2 = st.query(0, 0, n - 1, 4, 10);

        System.out.println("\nMaximum Water Level in Zones [4,10] : " + ans2);
    }
}