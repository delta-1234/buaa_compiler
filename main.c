const int Mod = 10007;

const int Map[2][2] = {
	{1, 1},
	{1, 0}
}, Bound[2] = {2, 2};

int T, f[2] = {1, 0};
int ori[2][2] = {
	{1, 0},
	{0, 0}
};

void calc(int a[][2], int b[][2]) {
	int i = 0, j = 0, k = 0;
	int Map[2][2];
	for (;i < Bound[0];) {
		j = 0;
		for (;j < Bound[1];) {
			k = 0;
			int sum = 0;
			for (;k < Bound[0];) {
				sum = (sum + a[i][k] * b[k][j] % Mod) % Mod;
				k = k + 1;
			}
			Map[i][j] = sum;
			j = j + 1;
		}
		i = i + 1;
	}
	i = 0;
	j = 0;
	for (;i < Bound[0];) {
		for (;j < Bound[1];) {
			a[i][j] = Map[i][j];
			j = j + 1;
		}
		i = i + 1;
	}
}

int getans(int a[]) {
 	int i = 0, sum = 0;
	for (;i < Bound[0];) {
		sum = sum + a[i];
		i = i + 1;
	}
	return sum;
}

void Copy(int a[][2]) {
	a[0][0] = Map[0][0]; a[0][1] = Map[0][1];
	a[1][0] = Map[1][0]; a[1][1] = Map[1][1];
}

int main() {
printf("%d\n",f[0]);

	T = getint();
	int temp[2][2];
	Copy(temp);
	for (;T;) {
		if (T / 2 * 2 != T) {
			calc(ori, temp);
		} else {}
		T = T / 2;
		calc(temp, temp);
	}
	int i = 0;
	for(;i < Bound[0];) {
		printf("f%d: %d\n", f[i], getans(ori[i]));
		i = i + 1;
	}
	printf("ori00:%d\n", ori[0][0]);
	return 0;
}