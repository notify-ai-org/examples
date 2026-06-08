<p align="center">
  <span style="font-size: 50px;">⚡</span>
</p>
<h1 align="center" style="border-bottom: none;">
  <span style="font-weight: 800;">Notify<span style="background: linear-gradient(135deg, #f59e0b 0%, #eab308 100%); -webkit-background-clip: text; -webkit-text-fill-color: transparent;">.ai</span></span>
</h1>
<p align="center"><b>Banking Example Application</b> — SDK Integration Demo</p>

---

## 📖 Overview

The `banking-app` module is a sample Spring Boot application demonstrating how to integrate the **Notify.ai** client SDK into a financial/banking services architecture. It defines banking operations (Logins, OTP verification, Transactions, Balance summaries) and showcases event interception using annotations.

## 🚀 Running Locally

You can run the banking sample application locally to test the event logging flow.

### Prerequisites
- **Notify.ai Control Plane**: Ensure the backend application (`access` module) is running on `http://localhost:8080`.

### Execution
From the root directory of the project, run:
```bash
mvn spring-boot:run -pl examples/banking-app
```
By default, the application runs on port **8091**.

### Testing the Integration
Once the application is running, trigger transactional events via HTTP requests:

```bash
# Trigger a secure login event
curl -X POST http://localhost:8091/banking/login \
  -H "Content-Type: application/json" \
  -d '{"accountId":"act-10123","device":"iPhone 15","ipAddress":"192.168.1.5"}'

# Submit a transaction (which generates events and triggers rules)
curl -X POST http://localhost:8091/banking/transaction \
  -H "Content-Type: application/json" \
  -d '{"accountId":"act-10123","amount":750.0,"targetAccount":"act-99220","description":"Monthly Rent Payment"}'
```
These events are intercepted by the client SDK and batched/pushed to the central control plane (`access` module) at port `8080` for processing.

---

## 👥 Developer Contact & Contributing

For questions, issues, or support regarding this module:
- **Lead Developer**: Rohan Naik ([rohan.naik07@github](https://github.com/rohan-naik07))
- **Email**: dev-support@notify.ai

### Contributing

We welcome contributions! Please follow these guidelines:
1. **Fork** the repository and create your branch from `master`.
2. Ensure your changes compile and all tests pass.
3. Follow the project's Java coding standards and naming conventions.
4. Submit a **Pull Request** with a detailed description of your changes.
