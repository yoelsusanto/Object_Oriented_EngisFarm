package src;

import java.util.*;

public class List<T> {
    protected Vector<T> data;
    protected int neff;

    public List() {
        data = new Vector<T>();
        neff=0;
    }

    public int getNeff() {
        return neff;
    }

    public int find(T p) {
        Iterator it = data.iterator();

        int i;
        if (p instanceof Product) {
            i=-1;

            while (it.hasNext()) {
                i++;
                if (((Product) p).getTypeProduct() == ((Product)it.next()).getTypeProduct()) {

                    return i;
                }
            }

        } else if (p instanceof FarmAnimal) {
            i =-1;

            while (it.hasNext()) {
                i++;
                if (((FarmAnimal) p).getTypeAnimal() == ((FarmAnimal)it.next()).getTypeAnimal()) {

                    return i;
                }
            }

        } else {
            return data.indexOf(p);
        }

        return -1;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void add(T element) {
        data.add(element);
        neff++;
    }

    public void removeIdx(int index) {
        if(index < data.size()) {
            data.remove(index);
            neff--;
        } else {
            System.out.println("Index not valid!");
        }
    }

    public void remove(T element) {
        int index = find(element);

        if (index==-1) {
            System.out.println("Item not found!");
        } else {
            data.remove(index);
            neff--;
        }
    }

    public T get(int index) {
        return data.get(index);
    }
}

