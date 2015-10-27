/*
	Block comment example.
*/

int i,j; // Global variables
int f(int a) { // f function
    return a;
}

void main() { // Main function
    int i,b; // Local variables
    double r;
    char c;
    int[10] v; // Array

    i = 1;
    r = 12.3 - 34.12E-3 * 2. + 3e3;
    c = '\126';

    b = v[2];
    if (1 && 2 || !0) {
        i = b;
    }

    while (i<10) {
        i=i+1;
        j=i;
    }
}