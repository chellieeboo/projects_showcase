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

### 3. News Feed Scraper (`scrape_news.py`)
- **Website:** quotes.toscrape.com
- **Data extracted:** Quote text, author, tags, and author profile link
- **Output:** `news_headlines.csv` and `news_headlines.json` (bonus JSON export)
- **Note:** This project originally targeted BBC News, but I switched to a scraping-practice site to keep the project fully compliant with target-site Terms of Service. It still demonstrates the same "news feed" style scraping (list of items, each with a title, author/source, and metadata).

## Sample Output

**`products_output.csv`**

| Title | Price | Rating | Availability |
|---|---|---|---|
| A Light in the Attic | £51.77 | Three | In stock |
| Tipping the Velvet | £53.74 | One | In stock |
| Sharp Objects | £47.82 | Four | In stock |

**`news_headlines.csv`**

| Title | Author | Tags |
|---|---|---|
| "The world as we have created it is a process of our thinking..." | Albert Einstein | change, deep-thoughts, thinking, world |
| "It is our choices, Harry, that show what we truly are..." | J.K. Rowling | abilities, choices |

Full sample files (CSV and JSON) are included in this folder as `products_output_sample.csv`, `news_headlines_sample.csv`, and `news_headlines_sample.json`, so anyone browsing the repo can preview the results without running the code.

## What I Learned
- How to use `requests` to fetch web pages
- How to parse HTML with `BeautifulSoup`
- How to extract multiple data points (title, price, rating, availability, author, tags)
- How to handle pagination (looping through multiple pages until the last one)
- How to handle errors gracefully (timeouts, failed requests, missing data)
- How to save data to CSV and JSON files
- How to structure code for readability with comments
- How to check whether a website is safe/legal to scrape before targeting it

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
   or
   ```bash
   python scrape_news.py
   ```

3. Check the generated CSV (and JSON, for the news scraper) file in the same folder for the results.

## Note
All scripts target [books.toscrape.com](http://books.toscrape.com) and [quotes.toscrape.com](http://quotes.toscrape.com), sites built specifically for practicing web scraping, so this is safe/legal scraping practice for a student portfolio.
