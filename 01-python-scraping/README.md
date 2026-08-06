# Python Web Scraping - Books

This is my first portfolio project. I built a web scraper that extracts book titles and prices from a demo bookstore website ([books.toscrape.com](http://books.toscrape.com/)).

The scraper automatically goes through **all 50 pages** of the site and collects **1000 books** in total.

## What I Learned

- How to use `requests` to fetch web pages
- How to use `BeautifulSoup` to parse HTML
- How to save data to CSV files
- How to handle errors (timeouts, failed requests, unexpected issues)
- How to handle pagination across multiple pages
- Best practices like setting a request timeout, using a User-Agent header, and adding delays between requests to be polite to the server

## How to Run

1. Install the required libraries:
   ```
   pip install requests beautifulsoup4
   ```
2. Run the script:
   ```
   python scraper.py
   ```
3. Output will be saved to `books_output.csv` in the same folder.

## Code

See [scraper.py](./scraper.py) for the full script.

## Sample Output

*(First 10 of 1000 scraped books — see the full results in [books_output.csv](./books_output.csv))*

| Title | Price |
|-------|-------|
| A Light in the Attic | £51.77 |
| Tipping the Velvet | £53.74 |
| Soumission | £50.10 |
| Sharp Objects | £47.82 |
| The Last Olympian | £52.15 |
| The Fault in Our Stars | £53.50 |
| The Book Thief | £56.38 |
| The Hobbit | £48.90 |
| The Alchemist | £44.25 |
| The Great Gatsby | £49.30 |
