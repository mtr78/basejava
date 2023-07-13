/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int i = 0;
        while (storage[i] != null) {
            storage[i] = null;
            i++;
        }
    }

    void save(Resume r) {
        int i = 0;
        while (storage[i] != null) {
            i++;
        }
        storage[i] = r;
    }

    Resume get(String uuid) {
        int i = 0;
        while (storage[i] != null && !storage[i].uuid.equals(uuid)) {
            i++;
        }
        return storage[i];
    }

    void delete(String uuid) {
        int size = this.size();
        boolean deleted = false;
        for (int i = 0; i < size; i++) {
            if (storage[i] != null && !storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                deleted = true;
            }
            if (deleted && i < size - 1 && storage[i] != null) {
                storage[i] = storage[i + 1];
            }
        }


    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int size = this.size();
        Resume[] allResumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            if (storage[i] != null) {
                allResumes[i] = storage[i];
            }
        }
        return allResumes;
    }

    int size() {
        int length = 0;
        for (int i = 0; i < 10000; i++) {
            if (storage[i] != null) {
                length++;
            } else if (storage[i] == null) {
                break;
            }
        }
        return length;
    }
}
