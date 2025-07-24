
## 🗳️ Online Voting System (Spring Boot REST API)

### 📌 Project Description

This is a Spring Boot-based REST API for a basic **online voting system** that allows:

* **Registered users** can vote **once** for any one candidate
* Tracks votes per candidate
* Prevents multiple voting using status field
* Includes **input validation**, **custom exception handling**, and **structured JSON responses**

---

### 🛠 Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate Validator
* MySQL / H2 (for DB)
* REST API + Swagger (optional)

---

### 👤 Step-by-Step API Usage

---

### 1️⃣ Add a User

**POST** `/api/users`

```json
{
  "name": "Vikas",
  "email": "vikas@gmail.com",
  "phone": "9876543210"
}
```

✅ On success, you get:

```json
{
  "id": 1,
  "name": "Vikas",
  "email": "vikas@gmail.com",
  "phone": "9876543210",
 
}
```

---

### 2️⃣ Add a Candidate

**POST** `/api/candidate`

```json
{
  "candidate": "Patil",
  "votes": 0
}
```

📌 Note:

* `candidate` must be unique
* `votes` must start from 0

✅ Response:

```json
{
  "id": 1,
  "candidate": "Patil",
  "votes": 0
}
```

---

### 3️⃣ Cast a Vote

**POST** `/api/candidate/vote`

**Request Parameters (in URL):**

```
?candidateName=Patil&email=vikas@gmail.com
```

📌 URL format:

```
http://localhost:8080/api/candidate/vote?candidateName=Patil&email=vikas@gmail.com
```

✅ Success response:

```json
"Vote submitted successfully!"
```

❌ If user already voted:

```json
"You have already voted!"
```

---

### 4️⃣ Get Candidate Info by Name

**GET** `/api/candidate/name/Patil`

✅ Response:

```json
{
  "id": 1,
  "candidate": "Patil",
  "votes": 1
}
```

---

### 5️⃣ Get Only Vote Count

**GET** `/api/candidate/Patil/votes`

```json
1
```

---

### ❌ Exception Handling

If candidate not found:

```json
{
  "timestamp": "2025-07-23T12:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Candidate with name 'XYZ' not found",
  "path": "/api/candidate/name/XYZ"
}
```

If validation fails (e.g., phone number blank):

```json
{
  "timestamp": "2025-07-23T12:32:00",
  "status": 400,
  "errors": {
    "phone": "Phone number must not be blank"
  }
}
```

---

### ✅ Validations

* `candidate` → must be unique & not null
* `votes` → must be numeric, minimum 0
* `email`, `name`, `phone` → required in user

---

### 📎 Sample Flow Summary

1. ✅ Add User → `POST /api/users`
2. ✅ Add Candidate → `POST /api/candidate`
3. ✅ Vote → `POST /api/candidate/vote?candidateName=Patil&email=vikas@gmail.com`
4. ✅ Fetch Candidate Info → `GET /api/candidate/name/Patil`

---

### 🧪 Testing with postman



### 👨‍💻 Developed By

**Vikas Paithane**
`Spring Boot Developer | Java `

