import requests
from bs4 import BeautifulSoup
import csv

def scrape_books():
    """Scrape book titles and prices from a demo bookstore"""
    url = "http://books.toscrape.com/"
    
    try:
        response = requests.get(url)
        response.raise_for_status()  # Check if request was successful
        
        soup = BeautifulSoup(response.text, 'html.parser')
        
        books = []
        for book in soup.find_all('article', class_='product_pod'):
            title = book.h3.a['title']
            price = book.find('p', class_='price_color').text
            books.append({'Title': title, 'Price': price})
        
        # Save to CSV
        with open('books_output.csv', 'w', newline='', encoding='utf-8') as file:
            writer = csv.DictWriter(file, fieldnames=['Title', 'Price'])
            writer.writeheader()
            writer.writerows(books)
        
        print(f"Successfully scraped {len(books)} books!")
        return books
        
    except requests.exceptions.RequestException as e:
        print(f" Error fetching the website: {e}")
        return []
    except Exception as e:
        print(f" An error occurred: {e}")
        return []

if __name__ == "__main__":
    scrape_books()
