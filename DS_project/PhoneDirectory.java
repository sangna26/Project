
import java.util.Scanner;


class ContactNode {
    Contact contact;
    ContactNode next;

    public ContactNode(Contact contact) {
        this.contact = contact;
        this.next = null;
    }
}
class ContactLinkedList {
    ContactNode head;

    public void append(Contact contact) {
        ContactNode newNode = new ContactNode(contact);
        if (head == null) {
            head = newNode;
        } else {
            ContactNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void display() {
        ContactNode current = head;
        while (current != null) {
            System.out.println("Name: " + current.contact.getName() + ", Phone: " + current.contact.getPhoneNumber());
            current = current.next;
        }
    }
}

class Group {
    String groupName;
    ContactLinkedList contacts;

    Group()
    {
        
    }
    public Group(String groupName) {
        this.groupName = groupName;
        this.contacts = new ContactLinkedList();
    }
}

class GroupNode {
    Group group;
    GroupNode next;

    public GroupNode(Group group) {
        this.group = group;
        this.next = null;
    }
}
class GroupLinkedList {
    GroupNode head;

    public void append(Group group) {
        GroupNode newNode = new GroupNode(group);
        if (head == null) {
            head = newNode;
        } else {
            GroupNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
}
 class Contact {
        
        private  String name;
        public void setName(String name) {
            this.name = name;
        }
        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        private String phoneNumber;
        Contact next;
        boolean isFavorite = false;
        private Contact[] contacts;
        Group[] groups;
        private int contactCount;
        private int groupCount;
        Scanner sc = new Scanner(System.in);
        CallHistoryStack stack = new CallHistoryStack();
        
        Contact()
        {
            
        }
        Contact(String name, String phoneNumber)
        {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.next= null;
        }
        public boolean checkIfNumberExists( String phoneNumber) {
            Contact temp = first;
            while (temp != null) {
                if (temp.getPhoneNumber().equals(phoneNumber)) {
                    return true;
                }
                temp = temp.next;
            }
            return false;
        }
    
        public boolean checkIfNameExists( String name) {
            Contact temp = first;
            while (temp != null) {
                if (temp.getName().equalsIgnoreCase(name)) {
                    return true;
                }
                temp = temp.next;
            }
            return false;
        }
        public  void updateContact( String name, String newPhoneNumber) {
            Contact temp = first;
            while (temp != null) {
                if (temp.getName().equalsIgnoreCase(name)) {
                    temp.phoneNumber = newPhoneNumber;
                    break;
                }
                temp = temp.next;
            }
            
        }
        

        public String getName ()
        {
            return name;
        }
        public String getPhoneNumber() {
            return phoneNumber;
        }
         
         Contact first = null;
         void insertAtFirst(String name, String phoneNumber)
         {
            Contact n = new Contact(name,phoneNumber);
                if(first==null)
                {
                    first=n;
                }
                else
                {
                    n.next=first;
                    first=n;
                }
        }
             void display()
            {
                if(first==null)
                {
                    System.out.println("List is empty");
                }
                else
                {
                    Contact temp = first;
                
                    while(temp!=null)
                    {
                        System.out.println(temp.name+"  -->  "+temp.phoneNumber+"  ");
                        
                        temp=temp.next;
                    }
                }
            }
    
            int size()
            {
                int n = 0;
                if(first==null)
                {
                    return 0;
                }
                else
                {
                    Contact temp = first;
                
                    while(temp!=null)
                    {
                    n++;
                        
                        temp=temp.next;
                    }
                    return n;
                }
            }
                Contact SearchContact(String name)
                {
                    int flag = 0;
                    if(first==null)
                    {
                        System.out.println("List is empty");
                        return null;
                    }
                    else
                    {
                        Contact temp = first;
                    
                        while(temp!=null)
                        {
                            if(name.equalsIgnoreCase(temp.name))
                            {
                            System.out.println(temp.name+"  -->  "+temp.phoneNumber+"  ");
                            System.out.println();
                            flag = 1;
                            return temp;
                            
                            }
                            temp=temp.next;
                        }
                        if(flag==0)
                        {
                            System.out.println("No contact found");
                            System.out.println();
                            return null;
                        }
                        return null;
                    }
                
                }
   
                void deletefromfirst()
                {
                    if(first==null)
                    {
                        System.out.println("No list available");
                    }
                    else
                    {
                        System.out.println(first.name+" is deleted");
                        first = first.next;
                        
                    }
                }
                public void deleteContact(String name) {
                    int flag = 0;
                    Contact dummy = first;
                    while (dummy != null) {
                        if (name.equalsIgnoreCase(dummy.name)) {
                            flag = 1;
                        }
                        dummy = dummy.next;
                    }
                    if (flag == 0) {
                        System.out.println("No contact found in this name");
                    } else {
                        if (first == null) {
                            System.out.println("no list are available");
                        } else {
                            if (first.name.equalsIgnoreCase(name)) {
                                System.out.println(first.name + " is deleted");
                                deletefromfirst();
                            } else {
                                Contact temp = first;
                                while (temp.next != null && !(temp.next.name).equalsIgnoreCase(name)) {
                                    temp = temp.next;
                                }
                                Contact del = temp.next;
                                System.out.println(del.name + " is deleted");
                                temp.next = del.next;
                                del.next = null;
                
                                // Update the call history stack
                               stack.updateCallHistoryStack(del.phoneNumber, "Unknown");
                            }
                        }
                    }
                }
                
                public void sortContacts() {
                    int n = size();
                    for (int i = 0; i < n - 1; i++) {
                    Contact current = first;
                        for (int j = 0; j < n - i - 1; j++) {
                            if (current.name.compareToIgnoreCase(current.next.name) > 0) {
                            
                                String tempName = current.name;
                                String tempPhoneNumber = current.phoneNumber;
                                current.name = current.next.name;
                                current.phoneNumber = current.next.phoneNumber;
                                current.next.name = tempName;
                                current.next.phoneNumber = tempPhoneNumber;
                            }
                            current = current.next;
                        }

                    }
                    display();
                }
   

                void makeCall()
                {
                    System.out.println("Enter Name you want to call ");
                    String s_name = sc.next();
                    int flag = 0;
                    if(first==null)
                    {
                        System.out.println("List is empty");
                    }
                    else
                    {
                        Contact temp = first;
                    
                        while(temp!=null)
                        {
                            if(s_name.equalsIgnoreCase(temp.name))
                            {
                                System.out.println("Calling " + temp.name + " at " + temp.phoneNumber);
                                flag = 1;
                                stack.push(temp);
                            }
                            temp=temp.next;
                        }
                        if(flag==0)
                        {
                            System.out.println("No contact found");
                            System.out.println();
                        }
                    }

                }

                public void groups()
                {
                    int choice;
                    GroupLinkedList groupList = new GroupLinkedList();
                    Scanner scanner = new Scanner(System.in);
            
                    
                    do
                    {
                    System.out.println("Enter choice ");
                    System.out.println("===================================");
                    System.out.println("___________________________________");
                    System.out.println("|  1. Create a new Group          |");
                    System.out.println("|  2. Delete Existing Group       |");
                    System.out.println("|  3. Add a contact to a Group    |");
                    System.out.println("|  4. Remove contact from Group   |");
                    System.out.println("|  5. List contacts in Group      |");
                    System.out.println("|  6. Display all Groups          |");
                    System.out.println("|  7. Back to main menu           |");
                    System.out.println("|_________________________________|");
                    System.out.println("===================================");
                    
                    choice = sc.nextInt();
                    sc.nextLine();
                    switch(choice)
                    {
                        case 1:
                        {
                            System.out.print("Enter the name of the new group: ");
                            String newGroupName = sc.nextLine();
                            createNewGroup(groupList, newGroupName);
                        }
                    break;
                        case 2:
                        {
                            System.out.print("Enter the name of the group to delete: ");
                            String groupNameToDelete = scanner.nextLine();
                            deleteGroup(groupList, groupNameToDelete);
                         }
                    break;
                        case 3:
                            {
                                System.out.print("Enter the name of the group to add the contact to: ");
                                String groupNameToAddContact = scanner.nextLine();
                                addContactToGroup(groupList, groupNameToAddContact);
                             }
                    break;
                         case 4:
                         {
                                System.out.print("Enter the name of the group to remove the contact from: ");
                                String groupNameToRemoveContact = scanner.nextLine();
                                removeContactFromGroup(groupList, groupNameToRemoveContact);
                         }
                      
                    break;
                         case 5:
                         {
                                System.out.print("Enter the name of the group to list contacts: ");
                                String groupNameToListContacts = scanner.nextLine();
                                listContactsInGroup(groupList, groupNameToListContacts);
                         }
                    
                    break;
                        case 6 :
                        {
                            displayAllGroups(groupList);
                        }
                        
                        break;
                case 7:
                    System.out.println("Exiting the program.");
                   break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                } }while(choice!=7);
            }
             void createNewGroup(GroupLinkedList groupList, String groupName) {
                Group newGroup = new Group(groupName);
                groupList.append(newGroup);
                System.out.println("Group '" + groupName + "' created.");
            }
        
            void deleteGroup(GroupLinkedList groupList, String groupName) {
                GroupNode current = groupList.head;
                GroupNode prev = null;
        
                while (current != null && !current.group.groupName.equals(groupName)) {
                    prev = current;
                    current = current.next;
                }
        
                if (current != null) {
                    // Found the group, remove it from the list
                    if (prev != null) {
                        prev.next = current.next;
                    } else {
                        groupList.head = current.next;
                    }
                    System.out.println("Group '" + groupName + "' deleted.");
                } else {
                    System.out.println("Group '" + groupName + "' not found.");
                }
            }
        
             void addContactToGroup(GroupLinkedList groupList, String groupName) {
                GroupNode current = groupList.head;
                int flag = 0;
        
                while (current != null && !current.group.groupName.equals(groupName)) {
                    current = current.next;
                }
        
                if (current != null) {
                    // Found the group, add a contact to it
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter the contact name: ");
                    String contactName = scanner.nextLine();
                    Contact c = SearchContact(contactName);
                    current.group.contacts.append(c);
                    flag = 1;
                    
                } else {
                    System.out.println("Group '" + groupName + "' not found.");
                }
                if(flag==0)
                {
                    System.out.println("Contact not added to group '" + groupName + "'.");
                }
               
            }
        
             void removeContactFromGroup(GroupLinkedList groupList, String groupName) {
                GroupNode current = groupList.head;
        
                while (current != null && !current.group.groupName.equals(groupName)) {
                    current = current.next;
                }
        
                if (current != null) {
                    // Found the group, remove a contact from it
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter the contact name to remove: ");
                    String contactNameToRemove = scanner.nextLine();
        
                    if (removeContact(current.group.contacts, contactNameToRemove)) {
                        System.out.println("Contact removed from group '" + groupName + "'.");
                    } else {
                        System.out.println("Contact '" + contactNameToRemove + "' not found in group '" + groupName + "'.");
                    }
                } else {
                    System.out.println("Group '" + groupName + "' not found.");
                }
            }
        
            boolean removeContact(ContactLinkedList contactList, String contactName) {
                ContactNode current = contactList.head;
                ContactNode prev = null;
        
                while (current != null && !current.contact.name.equals(contactName)) {
                    prev = current;
                    current = current.next;
                }
        
                if (current != null) {
                    // Found the contact, remove it from the list
                    if (prev != null) {
                        prev.next = current.next;
                    } else {
                        contactList.head = current.next;
                    }
                    return true;
                }
        
                return false;
            }
             void listContactsInGroup(GroupLinkedList groupList, String groupName) {
                GroupNode current = groupList.head;
            
                while (current != null) {
                    if (current.group.groupName.equals(groupName)) {
                        System.out.println("Contacts in group '" + groupName + "':");
                        
                        // Check if the group has contacts
                        if (current.group.contacts.head != null) {
                            current.group.contacts.display();
                        } else {
                            System.out.println("No contacts in this group.");
                        }
                        return;
                    }
                    current = current.next;
                }
            
                System.out.println("Group '" + groupName + "' not found.");
            }
            void displayAllGroups(GroupLinkedList groupList) {
                GroupNode current = groupList.head;
            
                if (current == null) {
                    System.out.println("No groups found.");
                    return;
                }
            
                System.out.println("List of Groups:");
                while (current != null) {
                    System.out.println("- " + current.group.groupName);
                    current = current.next;
                }
            }
    
                
public void markAsFavorite(String name) {
    Contact current = first;
    while (current != null) {
        if (current.name.equalsIgnoreCase(name)) {
            current.isFavorite = true; // Mark as favorite
            System.out.println(name + " has been marked as a favorite.");
            return;
        }
        current = current.next;
    }
    System.out.println(name + " not found in contacts.");
}

// Update the listFavorites method in the Contact class
public void listFavorites() {
    System.out.println("Favorite Contacts:");
    int flag = 0;
    Contact current = first;
    while (current != null) {
        if (current.isFavorite) {
            System.out.println(current.name + " - " + current.phoneNumber);
            flag = 1;
        }
        current = current.next;
    }
    if(flag==0)
    {
        System.out.println("No favorite contact found");
    }
}
public void displayCallHistory()
{
    stack.displayCallHistory();
}
public void clearHistory()
{
    stack.clearHistory();
}


public Object getGroupName() {
    return null;
}
}

class CallHistoryNode {
    Contact contact;
    CallHistoryNode next;

    public CallHistoryNode(Contact contact) {
        this.contact = contact;
        this.next = null;
    }
}
 class CallHistoryStack {
    private CallHistoryNode top;

    public CallHistoryStack() {
        top = null;
    }

    public void push(Contact contact) {
        CallHistoryNode newNode = new CallHistoryNode(contact);
        newNode.next = top;
        top = newNode;
    }

    public void pop(String name) {
        if (top == null) {
            System.out.println("No history");
        } else {
            Contact deletedContact = top.contact;
            top = top.next;
            System.out.println(deletedContact.getName() + " = " + deletedContact.getPhoneNumber() + " is deleted");
        }
    }

    public void clearHistory() {
        top = null;
        System.out.println("Call history cleared.");
    }

    public void displayCallHistory() {
        if (top == null) {
            System.out.println("No history");
        } else {
            CallHistoryNode current = top;
            while (current != null) {
                Contact call = current.contact;
                System.out.println(call.getName() + " = " + call.getPhoneNumber());
                current = current.next;
            }
        }
    }
    public void updateCallHistoryStack(String phoneNumber, String newName) {
    CallHistoryNode current = top;
    while (current != null) {
        if (current.contact.getPhoneNumber().equals(phoneNumber)) {
            current.contact.setName(newName);
            break;
        }
        current = current.next;
    }
}

}
class PhoneBook
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Contact contacts = new Contact();
        
        System.out.println();
        System.out.println("|=======================================|");
        System.out.println("|                                       |");
        System.out.println("|      WELCOME TO PHONE DIRECTORY       |");
        System.out.println("|                                       |");
        System.out.println("|_______________________________________|");
        System.out.println();
         contacts.insertAtFirst("Nishita", "9104188101");
         contacts.insertAtFirst("Saloni","7572888919");
         contacts.insertAtFirst("Yashvi", "7383864989");
         contacts.insertAtFirst("Tanvi","9428951977");
         contacts.insertAtFirst("Sangana","7984795244");             
                       
        while(true)
        {
                System.out.println("Enter your choice");
                System.out.println("|=======================================|");
                System.out.println("|                                       |");
                System.out.println("| 1. add contacts                       |");
                System.out.println("| 2. display contacts                   |");
                System.out.println("| 3. search contacts                    |");
                System.out.println("| 4. delete contacts                    |");
                System.out.println("| 5. sort contact by alphabet           |");
                System.out.println("| 6. Make call                          |");
                System.out.println("| 7. display call history               |");
                System.out.println("| 8. favourite contact                  |");
                System.out.println("| 9. clear call history                 |");
                System.out.println("| 10. group call                        |");
                System.out.println("| 11. exit                              |");
                System.out.println("|_______________________________________|");
                int choice = sc.nextInt();
                System.out.println();
                switch(choice)
                {
                    case 1 :
                    {
                        
                        System.out.println("Enter Name");
                        String name = sc.next();
                        Contact first  = null;
                        String mobile_no = "";
                        while (true) {
                            System.out.println("Enter number");
                            mobile_no = sc.next();
                            if (isValidNumber(mobile_no)) {
                                break;
                            } else {
                                System.out.println("Invalid number. Enter a valid 10-digit number or number start with 9,8,7,6.");
                            }
                        }

                        if (contacts.checkIfNumberExists(mobile_no) || contacts.checkIfNameExists(name)) {
                            System.out.println();
                            System.out.println("Contact already exists");
                            System.out.println();
            
                            System.out.println("Do you want to resave or save as another name? (resave/save_as_another_name)");
                            String c = sc.next();
            
                            if (c.equals("resave")) {
                                 contacts.updateContact( name, mobile_no);
                                System.out.println();
                                System.out.println("Contact updated successfully");
                                System.out.println();
                            }
                            
                             else if (c.equals("save_as_another_name")) {
                                System.out.println("Enter new name: ");
                                String newName = sc.next();
            
                                contacts.insertAtFirst( newName,mobile_no);
                                System.out.println();
                                System.out.println("New contact added successfully");
                                System.out.println();
                            }
                        } else {
                            contacts.insertAtFirst( name, mobile_no);
                            System.out.println();
                            System.out.println("Contact added successfully");
                            System.out.println();
                        }

                       

                    }
                    
                    break;
                    case 2 :
                    {
                        System.out.println("1. Show all contacts");
                        System.out.println("2. Show favorite contacts");
                        int displayChoice = sc.nextInt();
                        sc.nextLine(); 
                        if (displayChoice == 1) {
                           contacts.display();; 
                        } else if (displayChoice == 2) {
                           contacts.listFavorites();
                        }
                    }
                    break;
                    case 3 :
                    {
                        System.out.println("Enter name you want to search");
                        String name = sc.next();
                        contacts.SearchContact(name);
                    }
                    break;
                    case 4 :
                    {
                        System.out.println("Enter name you want to delete");
                        String name = sc.next();
                        contacts.deleteContact(name);
                    }
                    break;
                    case 5 :
                    {
                        contacts.sortContacts();
                    }
                    break;
                    case 6 :
                    {
                        contacts.makeCall();
                    }
                    break;
                    case 7 :
                    {
                       contacts.displayCallHistory();
                    }
                    break;
                    case 8 :
                    {
                        System.out.println("Enter the name of the contact to toggle favorite status:");
                        String contactName = sc.next();
                        contacts.markAsFavorite(contactName);
                    }
                    break;
                   case 9 :
                   {
                      contacts.clearHistory();
                   }
                   break;
                  case 10:
                  {
                      contacts.groups();
                  }
                  break;
                   case 11 :
                   {
                    System.out.println("Exiting phone directory...........");
                    System.exit(0);
                   }
                  
                   default:
                   {
                    System.out.println("Enter valid choice");
                   }

                }
        }

    }
    private static boolean isValidNumber(String mobile_no) {
        if (mobile_no.length() != 10) {
            return false;
        }
        
        char firstDigit = mobile_no.charAt(0);
        
        // Check if the first digit is 9, 8, 7, or 6
        if (firstDigit != '9' && firstDigit != '8' && firstDigit != '7' && firstDigit != '6') {
            return false;
        }
        
        for (int i = 0; i < mobile_no.length(); i++) {
            char digit = mobile_no.charAt(i);
            if (!Character.isDigit(digit)) {
                return false;
            }
        }
        
        return true;
    }
    
}