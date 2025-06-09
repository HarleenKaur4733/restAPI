# restAPI


---

## ✅ `@Controller` (Traditional MVC Controller)

* **Used in:** Traditional Spring MVC web applications
* **Purpose:** Returns **HTML views** using templates (like JSP, Thymeleaf)
* **Needs:** You usually write `@ResponseBody` manually when returning raw data like JSON

### Example:

```java
@Controller
public class MyController {
    
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Controller";
    }
}
```

> ⚠️ Without `@ResponseBody`, it will try to find a view (like a JSP file) named `hello`.

---

## ✅ `@RestController` (Simplified for REST APIs)

* **Used in:** RESTful APIs (like JSON-based services)
* **Purpose:** Returns **data** (usually JSON/XML) directly in the response body
* **Combination:** It’s shorthand for `@Controller + @ResponseBody`

### Example:

```java
@RestController
public class MyRestController {
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello from RestController";
    }
}
```

> ✔️ Automatically converts the return value to JSON or XML using Jackson or other message converters.

---

## 🔁 Summary Table

| Feature                 | `@Controller`           | `@RestController`       |
| ----------------------- | ----------------------- | ----------------------- |
| Returns                 | Views (HTML, JSP, etc.) | Data (JSON, XML, etc.)  |
| Use with REST APIs      | ❌ Not preferred         | ✅ Recommended           |
| Adds @ResponseBody      | ❌ Manually required     | ✅ Automatically applied |
| Used with View Resolver | ✅ Yes                   | ❌ No view rendering     |

---

## 💡 When to Use What?

* Use **`@RestController`** for APIs that return data.
* Use **`@Controller`** for web apps that render HTML pages.




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


