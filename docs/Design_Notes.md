# Design Notes

## Why You Used `ArrayList` Instead of Array
`ArrayList` was chosen over arrays because:
- It provides dynamic resizing, which eliminates the need to manually manage the size of the collection.
- It offers built-in methods like `add()`, `remove()`, and `contains()` for easier manipulation of data.
- It is part of the Java Collections Framework, making it more versatile and compatible with other utilities.

## Where You Used Static Members and Why
Static members were used in the following scenarios:
- **Constants**: For example, `static final` constants were used to define fixed values like error messages or configuration keys, ensuring they are accessible without creating an instance.
- **Utility Methods**: Static methods were used in utility classes to provide common functionality (e.g., validation methods) without requiring object instantiation.

Static members were used to reduce memory overhead and ensure shared access across all instances of a class.

## Where You Used Inheritance and What You Gained From It
Inheritance was used in the following scenarios:
- **Service Layer**: Common functionality across services (e.g., logging, validation) was abstracted into a base service class, which was extended by specific service implementations like `StudentServiceImpl` and `CourseServiceImpl`.
- **Entities**: If applicable, base entity classes were used to define shared fields (e.g., `id`, `createdAt`) for all entities.

By using inheritance:
- Code reusability was improved, reducing duplication.
- Common behavior was centralized, making the code easier to maintain and extend.