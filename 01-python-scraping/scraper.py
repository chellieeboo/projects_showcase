# ==========================================================
# Simple Web Scraper - books.toscrape.com
# This script scrapes book data (title, price, rating,
# availability) from all pages of the site and saves it
# to a CSV file for easy viewing in Excel/Google Sheets.
# ==========================================================

import requests           # for sending HTTP requests to the website
from bs4 import BeautifulSoup  # for parsing the HTML we get back
import csv                # for writing our results to a CSV file
import time                # for adding delays between requests


def scrape_products():
    """Scrape all product data from books.toscrape.com (all pages)"""

    # This is the homepage of the site (page 1)
    base_url = "http://books.toscrape.com/"
    # This is the URL pattern for pages 2 onwards, {} gets replaced with the page number
    page_url_template = "http://books.toscrape.com/catalogue/page-{}.html"

    # Some websites block requests that don't look like they came from a browser.
    # Sending a User-Agent header makes our request look more like a normal browser visit.
    headers = {
        'User-Agent': 'Mozilla/5.0 (compatible; StudentScraper/1.0)'
    }

    # The site shows ratings as words (e.g. "Three") inside the HTML class name.
    # This dictionary lets us convert those words into actual numbers (1-5)
    # so the data is easier to sort/filter later.
    rating_map = {
        'One': 1, 'Two': 2, 'Three': 3, 'Four': 4, 'Five': 5
    }

    products = []   # this list will hold all the scraped books as dictionaries
    page_num = 1    # we start scraping from page 1

    try:
        # Keep looping through pages until there are no more pages left
        while True:
            # Page 1 uses the base URL, every other page uses the paginated URL
            url = base_url if page_num == 1 else page_url_template.format(page_num)

            # Send the GET request. timeout=10 means: if the server takes longer
            # than 10 seconds to respond, stop waiting and raise an error instead
            # of hanging forever.
            response = requests.get(url, headers=headers, timeout=10)

            # If the page doesn't exist (404), it means we've scraped every page
            # already, so we stop the loop here.
            if response.status_code == 404:
                break
            response.raise_for_status()  # raises an error if something else went wrong (like 500)

            # Parse the HTML content of the page so we can search through it
            soup = BeautifulSoup(response.text, 'html.parser')

            # Each book on the page is stored inside an <article class="product_pod"> tag
            books = soup.find_all('article', class_='product_pod')

            # Safety check: if no books were found, we've probably run out of pages too
            if not books:
                break

            # Loop through every book found on this page and extract its info
            for book in books:
                # --- Title ---
                # The book title is stored in the "title" attribute of the <a> tag
                title = book.h3.a['title']

                # --- Price ---
                # Get the raw price text first (e.g. "£51.77")
                price_text = book.find('p', class_='price_color').text.strip()
                # Clean it up by removing the currency symbol so we can convert it to a number
                price_clean = price_text.replace('£', '').replace('Â', '').strip()
                try:
                    price_value = float(price_clean)  # numeric version, useful for sorting/math
                except ValueError:
                    price_value = None  # just in case the price format is unexpected

                # --- Rating ---
                # The star rating is stored as a CSS class like "star-rating Three"
                rating_element = book.find('p', class_='star-rating')
                rating_word = rating_element['class'][1] if rating_element else 'No rating'
                rating_number = rating_map.get(rating_word, None)  # convert word to number

                # --- Availability ---
                availability = book.find('p', class_='instock availability')
                in_stock = availability.text.strip() if availability else 'Unknown'

                # Save this book's data as a dictionary and add it to our products list
                products.append({
                    'Title': title,
                    'Price': price_text,
                    'Price (float)': price_value,
                    'Rating': rating_word,
                    'Rating (number)': rating_number,
                    'Availability': in_stock
                })

            # Let us know in the terminal that this page is done
            print(f"📄 Scraped page {page_num} ({len(books)} books)")

            page_num += 1   # move on to the next page
            time.sleep(1)   # wait 1 second before the next request, so we don't spam the server

        # ------------------------------------------------
        # Once all pages are scraped, save everything to CSV
        # ------------------------------------------------
        filename = 'products_output.csv'
        # encoding='utf-8-sig' makes sure special characters (like £) show up correctly in Excel
        with open(filename, 'w', newline='', encoding='utf-8-sig') as file:
            fieldnames = ['Title', 'Price', 'Price (float)', 'Rating', 'Rating (number)', 'Availability']
            writer = csv.DictWriter(file, fieldnames=fieldnames)
            writer.writeheader()       # write the column headers first
            writer.writerows(products) # write all the book rows

        print(f"\n✅ Successfully scraped {len(products)} products across {page_num - 1} pages!")
        print(f"📁 Data saved to {filename}")

        # Print a sample so we can quickly check the data looks correct
        if products:
            print("\n📊 Sample Data:")
            print(f"Title: {products[0]['Title']}")
            print(f"Price: {products[0]['Price']}")
            print(f"Rating: {products[0]['Rating']} ({products[0]['Rating (number)']}/5)")
            print(f"Available: {products[0]['Availability']}")

        return products

    # If something goes wrong with the request itself (no internet, site down, etc.)
    except requests.exceptions.RequestException as e:
        print(f"❌ Error fetching website: {e}")
        return []
    # Catch-all for any other unexpected error, so the script doesn't just crash silently
    except Exception as e:
        print(f"❌ An unexpected error occurred: {e}")
        return []


# This makes sure scrape_products() only runs when the script is run directly,
# not when it's imported into another file
if __name__ == "__main__":
    scrape_products()
