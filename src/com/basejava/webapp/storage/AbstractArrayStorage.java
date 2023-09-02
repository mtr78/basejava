package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    protected final String errorResumeNotFound = "Нет такого резюме";

    protected int size;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0 && size <= storage.length) {
            insertElement(r, index);
            size++;
        } else if (index >= 0) {
            System.out.println("Резюме уже есть");
        } else {
            System.out.println("Переполнение storage");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            fillRemovedElement(index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println(errorResumeNotFound);
        }
    }


    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println(errorResumeNotFound);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillRemovedElement(int index);
}
