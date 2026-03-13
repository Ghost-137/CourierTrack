
## CourierTrack: Freelance Delivery Finance System

# 📌 Project Overview

CourierTrack is a secure, server-side web application built with Spring Boot. It is designed specifically for gig-economy workers (such as couriers for platforms like Wolt or Foodora) to track their true net income. While delivery apps show gross earnings, they do not account for vehicle expenses, fuel, or tax-deductible kilometers. This application solves that problem by providing a centralized dashboard for shift logging and expense tracking.

# 🎯 The Problem It Solves

Independent contractors face unique accounting challenges. At the end of a shift, a courier needs to know their actual profit and log their driven kilometers for Finnish tax deductions. This application automates that business logic, acting as a lightweight, mobile-friendly accounting tool.


🛠️ Technology Stack

    Backend: Java 17, Spring Boot 4.0.3

    Architecture: Layered MVC (Controller, Service, Repository)

    Database: * Development: H2 In-Memory Database

        Production: MariaDB

    Security: Spring Security 6 (BCrypt password hashing, Role-Based Access)

    Frontend: Thymeleaf (Server-Side Rendering) with Bootstrap 5


# 🚀 Core Features

    User Authentication: Full login and registration system. Users only have access to their own financial data.

    Shift Management: Log daily work shifts including start/end times, gross app earnings, and total kilometers driven.

    Expense Tracking: Record daily business expenses (fuel, gear, phone bills).

    Analytics Dashboard: Automatically calculates gross hourly wage, total expenses, and actual net profit.

    Independent Feature: Implementation of Password Reset via Email using JavaMailSender (or OAuth2 Google Login), demonstrating self-taught research outside the course curriculum.


# 🗄️ Database Design

The system utilizes a relational database with the following core entities:

    AppUser: Handles authentication and holds user details (id, username, password, role).

    Shift: Records work sessions (id, date, hours_worked, gross_earnings, kilometers_driven, user_id).

    Expense: Tracks costs (id, date, amount, category, description, user_id).