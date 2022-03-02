package ru.m210projects;

import java.util.Arrays;

public class IntArrayList implements IntList {
    private Integer[] array;
    private int size;

    public IntArrayList(int size) {
        array = new Integer[size];
    }

    public IntArrayList(Integer[] src) {
        array = new Integer[src.length];
        System.arraycopy(src, 0, array, 0, src.length);
        size = src.length;
    }

    private void extend() {
        Integer[] newarray = new Integer[Math.max(16, size * size)];
        System.arraycopy(array, 0, newarray, 0, size);
        array = newarray;
    }

    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.

    @Override
    public Integer add(Integer item) {
        if(item == null)
            throw new NullPointerException("Item shouldn't be NULL");

        if(size >= array.length - 1) {
            extend();
        }

        array[size++] = item;
        return item;
    }

    // Добавление элемента
    // на определенную позицию списка.
    // Если выходит за пределы фактического
    // количества элементов или массива,
    // выбросить исключение.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.

    @Override
    public Integer add(int index, Integer item) {
        if(item == null)
            throw new NullPointerException("Item shouldn't be NULL");

        if(index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index + " out of bounds " + size);
        }

        if(size == array.length) {
            extend();
        }

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;

        return item;
    }

    // Установить элемент
    // на определенную позицию,
    // затерев существующий.
    // Выбросить исключение,
    // если индекс меньше
    // фактического количества элементов
    // или выходит за пределы массива.

    @Override
    public Integer set(int index, Integer item) {
        if(item == null)
            throw new NullPointerException("Item shouldn't be NULL");

        if(index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index + " out of bounds " + size);
        }

        array[index] = item;
        return item;
    }

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.

    @Override
    public Integer remove(Integer item) {
        if(item == null)
            throw new NullPointerException("Item shouldn't be NULL");

        int index = indexOf(item);
        if(index == -1)
            throw new RuntimeException("item " + item + "is not found");
        return remove(index);
    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.

    @Override
    public Integer remove(int index) {
        if(index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index + " out of bounds " + size);
        }

        Integer obj = array[index];
        System.arraycopy(array, index + 1, array, index, size);
        size--;

        return obj;
    }

    // Проверка на существование элемента.
    // Вернуть true/false;

    @Override
    public boolean contains(Integer element) {
        if(element == null) {
            throw new NullPointerException("Element shouldn't be NULL");
        }

        return skyProBinarySearch(getSortedArray(), element);
    }

    private boolean skyProBinarySearch(Integer[] arr, Integer element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element.equals(arr[mid])) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.

    @Override
    public int indexOf(Integer item) {
        if(item == null)
            throw new NullPointerException("Item shouldn't be NULL");

        for(int i = 0; i < size; i++) {
            if(array[i].equals(item)) {
                return i;
            }
        }

        return -1;
    }

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.

    @Override
    public int lastIndexOf(Integer item) {
        if(item == null)
            throw new NullPointerException("Item shouldn't be NULL");

        for(int i = size - 1; i >= 0; i--) {
            if(array[i].equals(item)) {
                return i;
            }
        }

        return -1;
    }

    // Получить элемент по индексу.
    // Вернуть элемент или исключение,
    // если выходит за рамки фактического
    // количества элементов.

    @Override
    public Integer get(int index) {
        if(index < 0 || index >= this.size) {
            throw new RuntimeException();
        }

        return array[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.

    @Override
    public boolean equals(IntList otherList) {
        if(otherList == null) {
            throw new NullPointerException("List shouldn't be NULL");
        }

        if(this == otherList)
            return true;

        if(size != otherList.size())
            return false;

        for(int i = 0; i < size; i++) {
            if(!array[i].equals(otherList.get(i))) {
                return false;
            }
        }

        return true;
    }

    // Вернуть фактическое количество элементов.

    @Override
    public int size() {
        return size;
    }

    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Удалить все элементы из списка.

    @Override
    public void clear() {
        size = 0;
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    private Integer[] getSortedArray() {
        Integer[] sortedArray = toArray();
        sortSelection(sortedArray);
//        Arrays.sort(sortedArray);
        return sortedArray;
    }

    private void swapElements(Integer[] arr, Integer indexA, Integer indexB) {
        Integer tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private void sortBubble(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swapElements(array, j, j + 1);
                }
            }
        }
    }

    private void sortSelection(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(array, i, minElementIndex);
        }
    }

    private void sortInsertion(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }

    public void sortTest() {
        long start = System.currentTimeMillis();
        sortBubble(toArray()); // 38943 ms
        System.out.println("sortBubble " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        sortSelection(toArray()); // 7743 ms
        System.out.println("sortSelection " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        sortInsertion(toArray()); // 11997 ms
        System.out.println("sortInsertion " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        Arrays.sort(toArray()); // 39 ms
        System.out.println("Arrays.sort " + (System.currentTimeMillis() - start) + " ms");
    }
}
