class Poly
{
    private Term first;
    private Term last;

    private class Term
    {
        private int coef;
        private int expo;
        private Term next;

        private Term (int coef, int expo, Term next)
        {
            this.coef = coef;
            this.expo = expo;
            this.next = next;
        }
    }


    public Poly ()
    {
        first = new Term (0, Integer.MAX_VALUE, null);
        last = first;
    }

    public boolean isZero()
    {
       return first.next == null;
    }

    public Poly minus ()
    {
        Poly result = new Poly();
        Term temp = this.first.next;
        while (temp != null)
        {
            result.plus(temp.coef * -1, temp.expo);
            temp = temp.next;
        }
        return result;
    }

    public Poly plus (Poly that)
    {
        Poly result = new Poly();
        Term left = this.first.next;
        Term right = that.first.next;
        if(this.isZero())
        {
            return that;
        }
        if (that.isZero()) //fix case with addition with empty term
        {
            return this;
        }
            while (left != null && right != null)
            {
                if (left.expo > right.expo)
                {
                    result = result.plus(left.coef, left.expo);
                    left = left.next;
                }
                else if (right.expo > left.expo)
                {
                    result = result.plus(right.coef, right.expo);
                    right = right.next;

                }
                else
                    {
                    if (right.coef + left.coef == 0)
                    {
                        right = right.next;
                        left = left.next;
                    }
                    else
                        {
                        int tempCoef = right.coef + left.coef;
                        result = result.plus(tempCoef, right.expo);
                        right = right.next;
                        left = left.next;
                    }
                }
            }
            if(left != null)
            {
                result.last.next = right;
            }
            else
            {  
                result.last.next = left;

            }
        return result;
    }

    public Poly plus (int coef, int expo) //adds terms to poly
    {
        if(coef == 0 || expo < 0 || expo >= last.expo)
        {
            throw new IllegalArgumentException("Zero");
        }
        else
            {
              this.last.next = new Term (coef, expo, null);
              this.last = last.next;
            }
        return this;
    }

    public String toString()
    {
        Term temp = this.first.next;
        StringBuilder builder = new StringBuilder();
        if(this.isZero()) {
            builder.append('0');
            return builder.toString();
        }
        builder.append(temp.coef).append('x').append(temp.expo).append(' ');
        temp = temp.next;
        while (temp.next != null)
        {
            if (temp.coef > 0)  //temp.next.coef > 0
            {
                builder.append('+').append(' ').append(temp.coef).append('x').append(temp.expo).append(' ');
                temp = temp.next;
            }
            else { //fix signs with subtraction
                int tempCoef = Math.abs(temp.coef);
                builder.append('-').append(' ').append(tempCoef).append('x').append(temp.expo).append(' ');
                temp = temp.next;
            }
        }
        if (temp.coef > 0) {
            builder.append('+').append(' ').append(temp.coef).append('x').append(temp.expo);
        }
        else{
            builder.append('-').append(' ').append(temp.coef).append('x').append(temp.expo);
        }
        return builder.toString();
    }
}
