# Python Web Scraping Portfolio

This folder contains my Python web scraping projects that demonstrate my ability to extract data from websites.

## Projects

### 1. Book Scraper (`scraper.py`)
- **Website:** books.toscrape.com
- **Data extracted:** Book titles and prices
- **Output:** `books_output.csv`

### 2. Product Scraper (`scrape_products.py`)
- **Website:** books.toscrape.com (e-commerce demo)
- **Data extracted:** Product titles, prices (raw + numeric), ratings (word + numeric), and availability
- **Coverage:** Scrapes **all pages** of the catalog (~1000 books), not just the homepage
- **Output:** `products_output.csv`

## Sample Output (`products_output.csv`)

| Title | Price | Rating | Availability |
|---|---|---|---|
| A Light in the Attic | £51.77 | Three | In stock |
| Tipping the Velvet | £53.74 | One | In stock |
| Sharp Objects | £47.82 | Four | In stock |

## What I Learned
- How to use `requests` to fetch web pages
- How to parse HTML with `BeautifulSoup`
- How to extract multiple data points (title, price, rating, availability)
- How to handle pagination (looping through multiple pages until the last one)
- How to handle errors gracefully (timeouts, failed requests, missing data)
- How to save data to CSV files
- How to structure code for readability with comments

## Requirements
- Python 3.x
- `requests`
- `beautifulsoup4`

## How to Run

1. Install required packages:
   ```bash
   pip install requests beautifulsoup4
   ```

2. Run the scraper you want:
   ```bash
   python scraper.py
   ```
   or
   ```bash
   python scrape_products.py
   ```

3. Check the generated CSV file in the same folder (e.g. `products_output.csv`) for the results.

## Note
Both scripts target [books.toscrape.com](http://books.toscrape.com), a site built specifically for practicing web scraping, so this is safe/legal scraping practice.
