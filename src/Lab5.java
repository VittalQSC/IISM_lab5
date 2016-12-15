/**
 * Created by vittal on 12/14/16.
 */
public class Lab5 {
    public static final int N = 10000;
    public static final int n = 10000;
    public static double[] pi = {0.5, 0.5};
    public static double[][] p = {{0.5, 0.5}, {0.5, 0.5}};

    public static void main(String[] args) {
        double[][] A = {{-0.4, -0.5}, {0.2, 0.8}};
        double[] f = {1., -1.};

        double[] h = {1., 0.};
        System.out.println("Numerical solve of system:");
        System.out.println("x = " + solve(A, f, h));
        h[0] = 0.0;
        h[1] = 1.1;
        System.out.println("y = " + solve(A, f, h));
        System.out.println("Exact value:");
        System.out.println("x = 1.84210");
        System.out.println("y = -3.1578947");
    }

    public static double solve(double[][] A, double[] f, double[] h) {
        double res = 0.0;
        double ran;
        int[] mar = new int[N + 1];
        double[] x = new double[n];
        double[] Q = new double[N + 1];

        for (int j = 0; j < n; j++) {
            x[j] = 0;
            for (int i = 0; i <= N; i++) {
                ran = Math.random();
                if (ran < pi[0])
                    mar[i] = 0;
                else mar[i] = 1;
            }

            //Вычисляем веса цепи Маркова
            Q[0] = pi[mar[0]] > 0 ? h[mar[0]] / pi[mar[0]] : 0;

            for (int k = 1; k <= N; k++) {
                if (p[mar[k - 1]][mar[k]] > 0)
                    Q[k] = Q[k - 1] * A[mar[k - 1]][mar[k]] / p[mar[k - 1]][mar[k]];
                else Q[k] = 0;
            }
            //////////////////////////////


            for (int k = 0; k <= N; k++)
                x[j] += Q[k] * f[mar[k]];
        }
        //Расчет мат. ожидания
        for (int j = 0; j < n; j++) {
            res += x[j];
        }
        return res / n;
    }
}
