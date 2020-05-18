class Zillion{

    int [] digits;

    public Zillion(int size) {
        digits = new int[size];
        digits.toString();

    }

    public void increment() {
        int i = digits.length - 1;
        while (i > 0) {
            if (digits[i] != 9) {
                digits[i] += 1;
                break;
            }

            else {
                digits[i] = 0;
                i =- 1;

            }
        }
    }

    public String toString() {
        String digitString = "";
        int x = digits.length - 1;

        while (x >0) {
            digitString = digitString + digits[x];
            x = -1;
        }
        return digitString;
    }
}

