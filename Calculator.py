def add(x, y):
    return x + y

def subtract(x, y):
    return x - y

def multiply(x, y):
    return x * y

def divide(x, y):
    if y == 0:
        return "Error! Division by zero."
    else:
        return x / y

def calculator():
    print("Select operation:")
    print("1. Addition")
    print("2. Subtraction")
    print("3. Multiplication")
    print("4. Division")
    
    choice = input("Enter choice (1/2/3/4): ")
    
    if choice in ['1', '2', '3', '4']:
        x = float(input("Enter first number: "))
        y = float(input("Enter second number: "))
        
        if choice == '1':
            print(f"{x} + {y} = {add(x, y)}")
        elif choice == '2':
            print(f"{x} - {y} = {subtract(x, y)}")
        elif choice == '3':
            print(f"{x} * {y} = {multiply(x, y)}
