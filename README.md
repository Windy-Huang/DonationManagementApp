# Donation Management Application
*Fundamental to a non-profit organization*

### Project Inspiration:
Back in high school, I volunteered at an organization called **Richmond Multicultural Community Service**, which serves
to provide support to new immigrants. Some instances of the services that we provided are English lessons, job fairs,
and tax clinics. The organization constantly receives funding and donations, so it needs a method of tracking them. When 
I was volunteering, I helped them create a spreadsheet with JavaScript customization. Now, looking back, I have acquired 
way more programming experience and knowledge in object-oriented design. Therefore, I want to help them **upgrade** the 
donation tracking method from a **spreadsheet** to a **Java desktop application**!

### Project Description:
The goal of the project is to help the **executive assistant** at RMCS keep a reliable record of all the donations 
received. Some targets that this application should achieve includes:
- Showing the total amount of donations received. 
- Keeping track of the donors' information, along with details of the transactions, such as amount and transaction type. 
- Being able to quickly find a donor by name, or create a report of the donors that have donated over a certain amount. 
- Handling a large amount of data; therefore, an area of improvement is to connect to SQL data using the JDBC library.
- Another improvement is the capability to back up data locally, providing ways to retrieve data in case of an accident.
- Ensuring security, protected by username and password.

### User Stories:

- As a user, I want to add a new donor record to the existing list of donors. 
- As a user, I want to add a new transaction to the list of transactions for a specific donor. 
- As a user, I want to search for a donor by name and view all the details of their donation transactions. 
- As a user, I want to view a list of donors who donated over a specified amount. 
- As a user, I want the login page to be protected by a username and password.
- As a user, I want the ability to mark transactions as archived after the current year ends, so they cannot be altered.
- As a user, when I quit the application, I have the option to save all the donor and transaction data.
- As a user, when I open the application, I have the option to load previously stored donor and transaction data.

### Instructions for Grader

- You can access the home screen of the application by entering the password "123."
- You can add multiple donors (Xs) to a panel (Y) by navigating to the toolbar, clicking "Add a Donor," and 
selecting the dropdown "Add Donor." Enter the required input as prompted, and remember to hit enter.
- You can display a subset of donors (Xs) that satisfy some criterion specified by the user by navigating to the 
toolbar, clicking "View All Donors," and selecting the dropdown "View with Threshold." Enter the minimum amount of 
donation required to display. The donors will appear in decreasing order of donation amount. 
- You can locate the visual component on the home screen. Once the application's data is updated, the visual component
will also update accordingly. 
- You can save the state of the application by navigating to the toolbar, clicking "Manage Files," and selecting the 
dropdown "Save File." Enter the name of the file to save. 
- You can reload the state of the application by navigating to the toolbar, clicking "Manage Files," and selecting the
dropdown "Load File." Enter the name of the file to load.

### Phase 4: Task 2
A representative sample of the event log:

EventLog

Sat Mar 30 21:33:45 PDT 2024
A new transaction added to windy

Sat Mar 30 21:34:15 PDT 2024
A new transaction added to lillian

Sat Mar 30 21:34:30 PDT 2024
lillian's transaction history viewed.

Sat Mar 30 21:34:35 PDT 2024
windy's transaction history viewed.

End of Session