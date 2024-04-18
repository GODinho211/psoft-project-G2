# US 20 - To register a client

## 1. Requirements Engineering

### 1.1. User Story Description

As Receptionist of the laboratory, I want to register a client.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> The citizen card Number, NHS Number and Tax Identification Number (TIF) should be unique for each client. The Sex attribute is optional. All other fields are required.

**From the client clarifications:**

> **n\a**

### 1.3. Acceptance Criteria

- AC01-1: AC20-01: The Citizen Card Number, NHS Number and Tax Identification Number (TIF) should be
  unique for each client.
- AC01-2: The Sex attribute is optional. All other fields are required.

### 1.4. Found out Dependencies

- No dependencies were found.

### 1.5 Input and Output Data

**Input Data:**

- Typed data:
    - Citizen Card Number
    - NHS Number
    - Birthdate
    - Gender
    - Tax ID
    - Phone Number
    - Email
    - Name


- Selected data:
    - n/a

**Output Data:**

- (In)success of the operation

### 1.6. System Sequence Diagram (SSD)

![US20-SSD](US20-SSD.svg)


### 1.7 Other Relevant Remarks

- n/a


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt

![US20-MD](US20-MD.svg)

### 2.2. Other Remarks

- n/a

## 3. Design - User Story Realization

### 3.1. Rationale


**The rationale grounds on the SSD interactions and the identified input/output data.**

| Interaction ID | Question: Which class is responsible for... | Answer             | Justification (with patterns)                            |
|:-------------  |:--------------------------------------------|:-------------------|:---------------------------------------------------------|
| Step 1  		 | Ask to create a new client                  | ClientsMenuView    | Because is the Class that manages the clients operations |
| Step 2  		 | Inputs the Data                             | CreateClientView   | Because is the Class that creates the clients            |
| Step 3  		 | Informs operation success                   | CreateClientView   | Because is the Class that show the confirmation          |


### Systematization

According to the taken rationale, the conceptual classes promoted to software classes are:

- Client

Other software classes (i.e. Pure Fabrication) identified:

- CreateClientView
- CreateClientController
- ClientService
- ClientRepository
- RepositoryFactory

### 3.2. Sequence Diagram (SD)

### 3.2.1. Previous Perspective (using Controllers for a Console UI)

**On this matter, Sprint C add no changes here.**

![US20-SD](US20-SD.svg)

**Notice that:**

- According to the Repository and Service patterns, the ClientContainer was split in two classes: ClientRepository and ClientService, respectively.
- The RepositoryFactory class is the result of applying the Abstract Factory pattern to ensure that the "{XXX}Repository" classes used are all from the same family of objects (i.e., they use the same persistence mechanism).
- No DTO was used, but it could have been.

### 3.2.2. REST API Perspective (reusing the domain logic)

**To accommodate the Sprint C requirements.**

**For brevity, sequence diagrams can be simplified to denote just what happens on the respective Controller.**

![US20-SD-RestAPI-Simplified](US20-SD-RestAPI-Simplified.svg)

### 3.3. Class Diagram (CD)

### 3.3.1. Previous Perspective (using Controllers for a Console UI)

Class diagram as resulting from the above sequence diagram and rationale:


![US20-CD](US20-CD.svg)

Adding to the previous class diagram the classes currently implementing the adopted interfaces.
Notice that the sequence diagram does not state which concrete implementation is being used since it may vary by configurations (cf. "config.ini" file).

![US20-CD_v2](US20-CD_v2.svg)

### 3.3.2. REST API Perspective (reusing the domain logic)

**To accommodate the Sprint C requirements.**

Just the new classes are shown.

![US20-CD-RestAPI](US20-CD-RestAPI.svg)

## 4. Tests

Three relevant test scenarios are highlighted next.
Other test were also specified.

**Test 1:** Check that it possible to create a new client with valid values.


      TEST_F(ClientListRepositoryFixture, UpdatingOneClient){
    EXPECT_TRUE(this->repo->isEmpty()); 
    shared_ptr<Client> cat = make_shared<Client>(L"1",L 911111119, L "Avenida");
    this->repo->save(cat);


## 5. Integration and Demo

The menu that integrates the CreateClientView is the ClientsMenuView.

    int ClientsMenuView::processMenuOption(int option) {
      int result = 0;
      BaseView *view;
      switch (option) {
        
        case 1:
        view = new CreateClientView(this->userToken);
        view->show();
        break;
        default:
        result = -1;
        break;
      }
      return result;
    }


## 6. Observations

- n/a