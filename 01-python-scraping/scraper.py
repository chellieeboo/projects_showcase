import requests
from bs4 import BeautifulSoup
import csv
import time


def scrape_books(base_url="http://books.toscrape.com/", output_file="books_output.csv"):
    """
    Scrape book titles and prices from all pages of a demo bookstore.

    Args:
        base_url (str): The starting URL of the bookstore.
        output_file (str): File path where scraped data will be saved as CSV.

    Returns:
        list[dict]: A list of dictionaries containing 'Title' and 'Price' for each book.
    """
    headers = {
        "User-Agent": (
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
            "AppleWebKit/537.36 (KHTML, like Gecko) "
            "Chrome/115.0 Safari/537.36"
        )
    }

    books = []
    url = base_url
    page_number = 1

    while url:
        try:
            print(f"Scraping page {page_number}: {url}")
            response = requests.get(url, headers=headers, timeout=10)
            response.raise_for_status()

            soup = BeautifulSoup(response.text, "html.parser")

            for book in soup.find_all("article", class_="product_pod"):
                title = book.h3.a["title"]
                price = book.find("p", class_="price_color").text
                books.append({"Title": title, "Price": price})

            # Find the "next" page link, if any
            next_button = soup.find("li", class_="next")
            if next_button:
                next_href = next_button.a["href"]
                # Handle relative URLs differently depending on current depth
                if "catalogue/" in url and not next_href.startswith("catalogue/"):
                    url = base_url + "catalogue/" + next_href
                else:
                    url = base_url + next_href if not url.endswith(next_href) else None
                page_number += 1
                time.sleep(1)  # be polite, avoid hammering the server
            else:
                url = None

        except requests.exceptions.Timeout:
            print("Error: The request timed out.")
            break
        except requests.exceptions.RequestException as e:
            print(f"Error fetching the website: {e}")
            break
        except Exception as e:
            print(f"An unexpected error occurred: {e}")
            break

    # Save results to CSV
    if books:
        with open(output_file, "w", newline="", encoding="utf-8") as file:
            writer = csv.DictWriter(file, fieldnames=["Title", "Price"])
            writer.writeheader()
            writer.writerows(books)
        print(f"Successfully scraped {len(books)} books across {page_number} page(s)!")
    else:
        print("No books were scraped.")

    return books


if __name__ == "__main__":
    scrape_books()