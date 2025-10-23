# Import the libraries we need
import requests  # Lets us send HTTP requests to websites
from bs4 import BeautifulSoup  # Used for parsing HTML content

# Ask the user for a website URL
url = input("Enter a website URL (include https://): ")

# Send a GET request to the website
response = requests.get(url)

# Check if the request was successful (status code 200)
if response.status_code == 200:
    # Parse the HTML content of the page
    soup = BeautifulSoup(response.text, "html.parser")

    # Example: Find all <h2> tags on the page (often used for article titles or headings)
    headings = soup.find_all("h2")

    # Print each heading text found
    print("\nHeadings found on the page:\n")
    for h in headings:
        # .get_text() extracts only the text from the tag, removing any HTML markup
        print("-", h.get_text(strip=True))

else:
    # If the page couldn't be reached, print an error
    print("Error: Could not retrieve the webpage. Please check the URL and try again.")