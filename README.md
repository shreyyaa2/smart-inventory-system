# 📦 Smart Inventory Management System (Java)

## 🚀 Overview

This is a **Java-based Inventory Management System** that allows users to manage products efficiently using file handling and basic OOP concepts.

The system supports operations like adding, viewing, searching, updating, and deleting products, along with a **low stock monitoring feature**.

---

## 🎯 Features

* ➕ Add new products
* 📋 View all products
* 🔍 Search product by ID
* ✏️ Update product details
* ❌ Delete product
* ⚠️ Low stock detection (quantity < 5)
* 💾 Data stored using file handling (`products.txt`)
* 🧵 Background thread for continuous stock monitoring

---

## 🛠️ Technologies Used

* Java
* OOP Concepts
* File Handling (BufferedReader, BufferedWriter)
* Collections (List, ArrayList)
* Multithreading

---

## 🧠 OOP Concepts Used

* **Encapsulation** → Product & Inventory classes
* **Abstraction** → Methods hide internal logic
* **Inheritance** → LowStockMonitor extends Thread
* **Polymorphism** → run() method overridden

---

## 📂 Project Structure

```
Product.java
Inventory.java
FileHandler.java
LowStockMonitor.java
details.java (Main class)
products.txt
```

---

## ▶️ How to Run

1. Compile the program:

```
javac details.java
```

2. Run the program:

```
java details
```

---

## 🔄 Program Flow

```
Start → Load data from file → Show menu → Perform operation → Save to file → Repeat
```

---

## ⚠️ Note

* Low stock is defined as **quantity less than 5**
* Data is persisted using a text file

---

## 🔮 Future Enhancements

* GUI (JavaFX / Swing)
* Database integration (MySQL)
* User authentication
* Notifications system

---

## 👩‍💻 Author

**Shreya B**

---

## 🙌 Acknowledgement

This project was developed as part of learning Java, OOP, and file handling concepts.
