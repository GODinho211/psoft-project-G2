# US 11 - Register as a reader

## 1. Requirements Engineering

### 1.1. User Story Description

As anonymous I want to register as a reader

### 1.2. Customer Specifications and Clarifications


> **Question:**
NOME:
É requisitado apenas o 1º e ultimo nome? 3, 4, 5 nomes?
São permitidos títulos? (Sr., Dr., …)
Apenas devem ser permitidas letras?
Deve ser apenas permitido o alfabeto latino? Devem ser permitido outros alfabetos (cirilico, grego, ...) ou sistemas de escrita (árabe, hebraico, ...)?
Pode ser deixado vazio?
Pode ter apenas espaços?
Existem algumas palavras proibidas?
EMAIL:
Um utilizador pode ter vários emails?
Um mesmo email pode pertencer a vários utilizadores?
É necessário validar se o email realmente existe?
Permite todos os dominios de email? Ou apenas um grupo (gmail.com, hotmail.com, isep.ipp.pt)? Se sim, quais?
DATA DE NASCIMENTO:
Existe uma idade mínima? (ex. nascido em 2024)
Existe uma idade máxima? (Ex. nascido em 1812)
A idade influencia algo? (Acesso a certas funcionalidades/livros, Recomendação de livros)
NÚMERO DE TELEFONE:
Um utilizador pode ter vários números de telefone?
Um mesmo número de telefone pode ser usado em vários leitores?
O número de telefone deve ser português ou admite outros países?
O número de telefone deve ser móvel ou pode ser fixo?
GDPR CONSENT:
Um simples sim/não é suficiente ou deve ter algum tipo de assinatura digital?
Que efeitos existem ao recusar? É permitido ao utilizador criar conta como leitor? Perde algumas funcionalidades?
Posso recusar apenas parcialmente?
NÚMERO DE LEITOR:
Deve ter um tamanho pré-definido (Ex. todos os nºs devem ter 8 caracteres)
Segue alguma regra (ex. certos números para certas bibliotecas/idades/…) ou é apenas auto-incremental?
12.Como leitor posso alterar todos os meus dados? Se não, quais?
13.Como bibliotecário posso ver todos os dados do leitor?
Se o utilizador recusar (total ou parcialmente) o GDPR, o bibliotecário vê menos dados do leitor? Se sim, quais?
>
>**Answer:**
11.NOME
> É requisitado apenas o 1º e ultimo nome? 3, 4, 5 nomes? - não necessitamos distinguir quantos nomes a pessoa tem
São permitidos títulos? (Sr., Dr., …) - nao há necessidade de capturar esta informação
Apenas devem ser permitidas letras? - qualquer caracter alfanumérico
Deve ser apenas permitido o alfabeto latino? Devem ser permitido outros alfabetos (cirilico, grego, ...) ou sistemas de escrita (árabe, hebraico, ...)? - basta considerar o alfabeto Latino
Pode ser deixado vazio? - não
Pode ter apenas espaços? - não
Existem algumas palavras proibidas? - sim. deve existir no sistema uma configuração de "palavras proibidas" que não são aceites no nome do Leitor
EMAIL:Um utilizador pode ter vários emails? - não
Um mesmo email pode pertencer a vários utilizadores? - não
É necessário validar se o email realmente existe? - não. basta que esteja no formato correto
Permite todos os dominios de email? Ou apenas um grupo (gmail.com, hotmail.com, isep.ipp.pt)? Se sim, quais? - qualquer dominio
DATA DE NASCIMENTO:
Existe uma idade mínima? (ex. nascido em 2024) - Leitor deve ter pelo menos 12 anos
Existe uma idade máxima? (Ex. nascido em 1812) - não
A idade influencia algo? (Acesso a certas funcionalidades/livros, Recomendação de livros) - de momento esse controlo é feito fisicamente pelo bibliotecário e fora do sistema
NÚMERO DE TELEFONE:
Um utilizador pode ter vários números de telefone? - não
Um mesmo número de telefone pode ser usado em vários leitores? - sim
O número de telefone deve ser português ou admite outros países? - basta considerar numeros portugueses de momento
O número de telefone deve ser móvel ou pode ser fixo? - ambos
GDPR CONSENT:
Um simples sim/não é suficiente ou deve ter algum tipo de assinatura digital? - simples "sim/não"
Que efeitos existem ao recusar? É permitido ao utilizador criar conta como leitor? Perde algumas funcionalidades? - o utilizador tem que aceitar a politica de privacidade de dados. se recusar não se poderá registar no sistema
Posso recusar apenas parcialmente? - é possivel recusar o consentimento de partilha de informação com terceiros, bem como o consentimento para efeitos de marketing
NÚMERO DE LEITOR:Deve ter um tamanho pré-definido (Ex. todos os nºs devem ter 8 caracteres) - não
Segue alguma regra (ex. certos números para certas bibliotecas/idades/…) ou é apenas auto-incremental? - é composto pelo ano de registo e um número sequencial, ex., 2023/1, 2024/19876
12.Como leitor posso alterar todos os meus dados? Se não, quais? - sim, à exceção do número de leitor
13.Como bibliotecário posso ver todos os dados do leitor? - não. a data de nascimento não deve ser visualizada, mas sim a idade do leitor
Se o utilizador recusar (total ou parcialmente) o GDPR, o bibliotecário vê menos dados do leitor? Se sim, quais? - não tem influência
### 1.3. Acceptance Criteria

- AC011-name: o User name nao pode ser deixado vazio, nao pode ter apenas espaços, 
deve existir no sistema uma configuração de "palavras proibidas" que não são aceites no nome do Leitor.
- AC011-email:Email tem que ser unico, nao pode ser repetido tem que estar no formato correto
- AC011-data de nascimento: tem que ter pelo menos 12 anos
- AC011-numero de telefone: tem que ser unico
- Ac011-GPRDconsent: o utilizador tem que aceitar a politica de privacidade de dados. se recusar não se poderá registar no sistema
- Ac011-numerodeleitor:é composto pelo ano de registo e um número sequencial, ex., 2023/1, 2024/19876
### 1.4. Found out Dependencies

- No dependencies were found.

### 1.5 Input and Output Data

**Input Data:**

- Typed data:
- 
  - id_user
  - name
  - email
  - date_of_birth
  - phone_number
  - GDPR_consent


- Selected data:
    - n/a

**Output Data:**

- (In)success of the operation

### 1.6. System Sequence Diagram (SSD)

![US11-SSD](US11-SSD.svg)


### 1.7 Other Relevant Remarks

- n/a

