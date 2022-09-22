#ifndef _DYN_ARRAY_H_
#define _DYN_ARRAY_H_

#include <iostream>
using namespace std;

template <typename T>
class DynArray {
 public:
  DynArray() {
    array = new T[capacity];
  }

  // copy constructor
  DynArray(const DynArray& other) {
    capacity = other.capacity;
    array = new T[capacity];
    for (int i = 0; i < other.numElements; ++i) {
      array[i] = other.array[i];
    }
    numElements = other.numElements;
    iteratorIndex = other.iteratorIndex;
  }

  // assignment operator
  DynArray& operator=(const DynArray& other) {
    while (capacity < other.capacity) {
      expand();
    }
    for (int i = 0; i < other.numElements; ++i) {
      array[i] = other.array[i];
    }
    numElements = other.numElements;
    iteratorIndex = other.iteratorIndex;
    return *this;
  }
  
  // destructor
  ~DynArray() {
    delete[] array;
  }
  
  T* add(T item) {
    if (numElements == capacity) {
      expand();
    }
    array[numElements] = item;
    numElements++;
    return &(array[numElements-1]);
  }
  
  T remove(T item) { // returns item that was removed; otherwise, empty object (default constructor)
    for (int i = 0; i < numElements; ++i) {
      if (array[i] == item) {
	T found = array[i];
	for (int j = i+1; j < numElements; ++j) {
	  array[j-1] = array[j];
	}
	numElements--;
	return found;
      }
    }
    return T();
  }
  
  bool has(T item) const {
    for (int i = 0; i < numElements; ++i) {
      if (array[i] == item) {
	return true;
      }
    }
    return false;
  }

  T* getItem(T item) {
    for (int i = 0; i < numElements; ++i) {
      if (array[i] == item) {
	return &(array[i]);
      }
    }
    return nullptr;
  }

  T* iteratorGet() {
    if (iteratorIndex < numElements) {
      iteratorIndex++;
      return &(array[iteratorIndex-1]);
    }
    iteratorIndex = 0;
    return nullptr;
  }

  void showAll() const{
    for (int i = 0; i < numElements; ++i) {
      cout << array[i] << endl;
    }
  }

  int getNumElements() const{
    return numElements;
  }
  
 private:
  int capacity = 10;
  int numElements = 0;
  int iteratorIndex = 0;
  T *array;

  void expand() {
    capacity += 20;
    T *newArray = new T[capacity];
    for (int i = 0; i < numElements; ++i) {
      newArray[i] = array[i];
    }
    delete[] array;
    array = newArray;
  }
};

#endif
