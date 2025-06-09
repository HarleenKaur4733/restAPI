# restAPI



### 🔍 What is `Optional` in Java?

`Optional<T>` is a **container object** introduced in Java 8 that may or may not contain a non-null value.

* If a value is **present**, `isPresent()` returns `true`, and you can get the value with `.get()`.
* If no value is present, it helps **avoid `NullPointerException`** by forcing you to explicitly handle the empty case.

---

### 🔍 Your Code Context:

```java
Optional<Employee> emp = employeeRepository.findById(id);
```

Here:

* `employeeRepository.findById(id)` returns an `Optional<Employee>`.
* It may contain an `Employee` if found, or be empty if no such employee exists with that ID.

---

### ✅ Why use `Optional`?

Before `Optional`, you'd typically write:

```java
Employee emp = employeeRepository.findById(id);
if(emp != null) {
    // update logic
}
```

This is error-prone — if you forget to check for `null`, it may throw a `NullPointerException`.

With `Optional`, you **force yourself to handle the missing value** case:

```java
if(emp.isPresent()) {
    Employee existingEmp = emp.get();
    // update logic
} else {
    // handle not found
}
```

---

### 🔐 Key Methods of `Optional`

| Method           | Description                                            |
| ---------------- | ------------------------------------------------------ |
| `isPresent()`    | Returns `true` if a value is present                   |
| `get()`          | Returns the value (only call if `isPresent()` is true) |
| `orElse(...)`    | Returns the value if present, or a default otherwise   |
| `ifPresent(...)` | Executes logic if a value is present                   |
| `orElseThrow()`  | Throws an exception if the value is not present        |

---

### ✅ Better alternative: `orElseThrow`

Instead of manually checking `isPresent()`, you could also write:

```java
Employee emp = employeeRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No employee found"));
```



---

### 🚀 Summary

* `Optional<Employee>` helps **safely handle null values** returned by `findById`.
* You avoid `NullPointerException` by checking `isPresent()` before using `.get()`.
* It makes your code **more readable, intentional, and less error-prone**.


