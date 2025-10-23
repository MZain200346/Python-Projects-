# Define functions for each operation
def add(x, y):
    # Returns the sum of x and y
    return x + y

def subtract(x, y):
    # Returns the difference between x and y
    return x - y

def multiply(x, y):
    # Returns the product of x and y
    return x * y

def divide(x, y):
    # Checks if y is zero to avoid division error
    if y == 0:
        return "Error! Division by zero."
    else:
        return x / y

# Main calculator function
def calculator():
    # Print the menu of available operations
    print("Select operation:")
    print("1. Addition")
    print("2. Subtraction")
    print("3. Multiplication")
    print("4. Division")
    
    # Ask the user to choose an operation
    choice = input("Enter choice (1/2/3/4): ")

    # Check if the user's choice is valid
    if choice in ['1', '2', '3', '4']:
        # Ask the user to input two numbers
        x = float(input("Enter first number: "))
        y = float(input("Enter second number: "))
        
        # Perform the operation based on the user's choice
        if choice == '1':
            print(f"{x} + {y} = {add(x, y)}")
        elif choice == '2':
            print(f"{x} - {y} = {subtract(x, y)}")
        elif choice == '3':
            print(f"{x} * {y} = {multiply(x, y)}")
        elif choice == '4':
            print(f"{x} / {y} = {divide(x, y)}")
    else:
        # Handle invalid input
        print("Invalid input! Please choose a number between 1 and 4.")

# Call the calculator function to start the program
calculator()
