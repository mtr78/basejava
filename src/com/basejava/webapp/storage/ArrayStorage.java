package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

    public int size;
    private final String errorResumeNotFound = "Нет такого резюме";

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = resumeExist(resume);
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println(errorResumeNotFound);
        }
    }

    public void save(Resume r) {
        if (size <= storage.length) {
            if (resumeExist(r) == -1) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Резюме уже есть");
            }
        } else {
            System.out.println("Переполнение storage");
        }
    }

    public Resume get(String uuid) {
        int index = resumeExist(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println(errorResumeNotFound);
            return null;
        }
    }

    public void delete(String uuid) {
        int index = resumeExist(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println(errorResumeNotFound);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    private int resumeExist(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage[i].getUuid(), resume.getUuid()))
                return i;
        }
        return -1;
    }

    private int resumeExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage[i].getUuid(), uuid))
                return i;
        }
        return -1;
    }


}
