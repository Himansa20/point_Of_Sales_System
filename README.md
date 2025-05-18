# Pharmacy Management System

A comprehensive Pharmacy Management System built with Spring Boot (Backend) and HTML/CSS/JavaScript (Frontend) that helps manage drug inventory, employee records, and sales transactions.

## Features

### Drug Management
- 🟢 Add, edit, and delete drugs from inventory
- 🔍 Search drugs by name with auto-suggestions
- 📊 View all drugs in a sortable table
- 📦 Track drug quantities and prices

### Employee Management
- 👥 Add and manage employee accounts
- 🔒 Role-based access control
- 📝 Employee performance tracking

### Sales Processing
- 💰 Create and manage customer bills
- 🧾 Automatic tax and total calculation
- 📈 Daily sales tracking and reporting

### Admin Dashboard
- 📊 Overview of pharmacy operations
- 🔔 Recent activity notifications
- ⚡ Quick access to all management sections

## Technologies Used

### Backend
- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **MySQL Database**
- **Spring Security**

### Frontend
- **HTML5**
- **CSS3**
- **JavaScript (ES6)**
- **Fetch API** for AJAX calls

## Getting Started

### Prerequisites
- Java JDK 17 or higher
- Maven 3.8+
- MySQL 8.0+
- Node.js (for optional frontend build)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/pharmacy-management-system.git
   cd pharmacy-management-system
   ```

2. **Database Setup**
   - Create a MySQL database named `pharmacy_db`
   - Update `application.properties` with your database credentials

3. **Build and Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the Application**
  Read the Project: Readme Important!.txt file

## Project Structure

```
pharmacy-management-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/pharmacy/
│   │   │       ├── config/       # Spring configuration
│   │   │       ├── controller/   # REST controllers
│   │   │       ├── entity/       # JPA entities
│   │   │       ├── repository/   # JPA repositories
│   │   │       ├── service/      # Business logic
│   │   │       └── PharmacyApplication.java
│   │   └── resources/
│   │       ├── static/           # Frontend assets
│   │       ├── templates/        # HTML pages
│   │       └── application.properties
├── frontend/                     # Frontend source files
├── README.md
└── pom.xml
```

## Screenshots

<img src="https://i.ibb.co/RGnSbyKf/Screenshot-28.png" alt="Screenshot-28" border="0" />
<img src="https://i.ibb.co/21JR9b4R/Screenshot-31.png" alt="Screenshot-31" border="0" />
<img src="https://i.ibb.co/mCVhzBq6/Screenshot-33.png" alt="Screenshot-33" border="0" />

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact

Project Maintainer - [TH Mudunkotuwa](mailto:thathsarahimansa@outlook.com)

Project Link:(https://github.com/Himansa20/pharmacy-management-system)

## Acknowledgments

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Awesome Readme Templates](https://github.com/othneildrew/Best-README-Template)
