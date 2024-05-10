# US 10 - Search book by gender

## 1. Requirements Engineering

### 1.1. User Story Description

As Librarian or Reader I want to search books by genre

### 1.2. Customer Specifications and Clarifications


>**Question:**
> n/a

>**Answer:**
> n/a


### 1.3. Acceptance Criteria

- AC010:
  - Devem ser mostrados todos os livros do género indicado. a pesquisa deve ser não exata, exemplo, se pesquisar por "fi" devem ser devolvidos os livros do género "ficcao" e do género "financas"
  

### 1.4. Found out Dependencies

- Author class
- Genre class

### 1.5 Input and Output Data

**Input Data:**

- Typed data:
  - n/a

- Selected data:
    - n/a

**Output Data:**

  - ISBN
  - Title
  - Description
  - GenderId
  - GenderDescription
  - AuthorId
  - AuthorName

### 1.6. System Sequence Diagram (SSD)

![US10-SSD](US10-SSD.png)


### 1.7 Other Relevant Remarks

- n/a

## 2. Design

### 2.1 Sequence Diagram (SD)

![US10-SD](US10-SD.png)

