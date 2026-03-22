# 🛒 AIUB Premium Store Management System

![Java](https://img.shields.io/badge/Language-Java-blue)
![OOP](https://img.shields.io/badge/Concept-OOP-green)
![GUI](https://img.shields.io/badge/UI-Java%20Swing-orange)
![Status](https://img.shields.io/badge/Project-Academic-lightgrey)

---

## 📌 Overview

A **Java GUI-based Store Management System** developed for the **Object-Oriented Programming (OOP-1)** course.

This application simulates a real-world store where users can purchase items, and admins can manage inventory and sales efficiently.

---

## 👥 Team

> 🎓 This was a **2-person group project** for the **OOP-1 course**

* 👤 **Member 1:** Your Name
* 👤 **Member 2:** Teammate Name

---

## 🚀 Features

### 🛍️ Customer Side

* Select items (Fruits, Vegetables, Meat)
* Real-time total calculation
* Payment method selection (Cash/Card)
* Receipt generation and storage

### 🔐 Admin Side

* Secure login system
* Update stock and price
* View complete sales history
* Track total accumulated sales

---

## 🧠 OOP Concepts Used

| Concept      | Implementation          |
| ------------ | ----------------------- |
| Interface    | `Purchasable`           |
| Abstraction  | `CategoryBase`          |
| Inheritance  | Fruits, Vegetable, Meat |
| Polymorphism | Method overriding       |
| Composition  | Checkout system         |

---

## 📂 Project Structure

```
AIUB-Store-Management
 ┣ GUI
 ┃ ┗ AIUBStoreGUI.java
 ┣ Class
 ┃ ┣ Fruits.java
 ┃ ┣ Vegetable.java
 ┃ ┣ Meat.java
 ┃ ┣ CategoryBase.java
 ┃ ┗ Checkout.java
 ┣ Interface
 ┃ ┗ Purchasable.java
 ┣ FileIo
 ┃ ┗ Admin.java
 ┣ Report
 ┃ ┣ inventory.txt
 ┃ ┣ sales.txt
 ┃ ┗ totalSales.txt
 ┗ Main.java
```

---

## ▶️ How to Run

### 🔧 Requirements

* Java JDK 8 or higher
* Any IDE (IntelliJ / Eclipse / NetBeans)

### ▶️ Steps

```
git clone https://github.com/your-username/aiub-store-management.git
```

1. Open the project in your IDE
2. Make sure the `Report` folder exists
3. Run `Main.java`

---

## 🔑 Admin Credentials

```
Username: aiub@edu
Password: pass1234
```

---

## 📦 Data Storage

| File           | Purpose              |
| -------------- | -------------------- |
| inventory.txt  | Stores stock & price |
| sales.txt      | Stores transactions  |
| totalSales.txt | Stores total sales   |

---

## 💡 How It Works

1. User selects product quantities
2. System calculates total instantly
3. On confirming sale:

   * Validates stock
   * Updates inventory
   * Saves receipt
   * Updates total sales

---

## 📊 Sample Output

```
AIUB PREMIUM STORE
------------------------
Apple           x5
Beef            x5
------------------------
TOTAL: ৳6295.00
```

---

## 📖 Learning Outcomes

* Practical implementation of OOP concepts
* Java Swing GUI development
* File handling (read/write)
* Team collaboration
* Real-world system design

---

## 🔮 Future Improvements

* Database integration (MySQL)
* JavaFX UI upgrade
* Online ordering system
* Advanced authentication system

---

## 📜 License

This project is for **academic purposes only**.

---

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!
