# Import the necessary modules
import random  # Used for generating random choices
import string  # Contains useful sets of characters like letters, digits, and punctuation

# Define a function that generates a random password
def generate_password(length=12):
    # Combine all possible characters:
    # lowercase + uppercase + digits + punctuation
    characters = string.ascii_letters + string.digits + string.punctuation

    # Use random.choice() inside a loop to select one character per iteration
    password = ''.join(random.choice(characters) for _ in range(length))

    # Return the final password string
    return password

# Ask the user how long they want their password to be
length = int(input("Enter desired password length: "))

# Generate a password of that length
new_password = generate_password(length)

# Display the generated password
print(f"\nYour new password is: {new_password}")
