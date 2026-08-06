# ==========================================================
# "News Feed" Style Scraper - quotes.toscrape.com
#
# Note: I originally tried scraping BBC News for this project,
# but BBC is a real commercial news site with Terms of Service
# that restrict automated scraping. To keep this project 100%
# safe and legal for my portfolio, I switched to
# quotes.toscrape.com instead — a site made specifically for
# practicing web scraping (same creators as books.toscrape.com).
#
# It has a "feed" layout similar to a news site: a list of
# items, each with a title/text, an author, and tags — so it
# still demonstrates the same scraping skills (requests,
# BeautifulSoup, error handling, CSV/JSON export).
# ==========================================================

import requests                  # lets Python send a request to a website, like opening it in a browser
from bs4 import BeautifulSoup    # helps us read/parse the HTML we get back
import csv                       # lets us save our data into a .csv file (like an Excel sheet)
import json                      # lets us save our data into a .json file too (bonus format)
from datetime import datetime    # lets us grab today's date to include in our data


def scrape_news():
    """Scrape 'news feed' style items (quotes) from quotes.toscrape.com"""

    # This is the page we are scraping. It's a demo site made for practicing
    # scraping, so it's totally fine to use it however we want.
    url = "https://quotes.toscrape.com/"

    # Some websites check the "User-Agent" to see if a real browser is visiting.
    # We fake a normal browser User-Agent here just to be safe/polite,
    # even though this demo site doesn't actually block scrapers.
    headers = {
        'User-Agent': 'Mozilla/5.0 (compatible; StudentScraper/1.0)'
    }

    try:
        print("🌐 Fetching data from quotes.toscrape.com...")

        # Send the request to the website and wait up to 10 seconds for a reply.
        # If it takes longer than that, Python will stop waiting and raise an error
        # (this stops our program from hanging forever).
        response = requests.get(url, headers=headers, timeout=10)

        # This will raise an error automatically if something went wrong,
        # like a 404 (page not found) or 500 (server error).
        response.raise_for_status()

        # Turn the raw HTML text into something we can easily search through.
        soup = BeautifulSoup(response.text, 'html.parser')

        news_articles = []  # this empty list will hold all our scraped items

        # Every quote on the page is wrapped inside a <div class="quote"> tag.
        # find_all() grabs ALL of them, not just the first one.
        quotes = soup.find_all('div', class_='quote')

        # Loop through every quote block we found on the page
        for quote in quotes:
            # --- "Title" (the quote text itself) ---
            # The quote text is inside <span class="text">
            title_elem = quote.find('span', class_='text')
            title = title_elem.text.strip() if title_elem else 'No text found'

            # --- Author ---
            # The author's name is inside <small class="author">
            author_elem = quote.find('small', class_='author')
            author = author_elem.text.strip() if author_elem else 'Unknown'

            # --- Link ---
            # Each quote has a small "(about)" link to the author's bio page
            link_elem = quote.find('a')
            link = link_elem.get('href') if link_elem else '#'

            # The link from the site is "relative" (e.g. "/author/Albert-Einstein"),
            # meaning it's missing the "https://quotes.toscrape.com" part.
            # We check if it's missing that part, and add it if needed,
            # so the link actually works if we click it later.
            if link and not link.startswith('http'):
                link = "https://quotes.toscrape.com" + link

            # --- Tags ---
            # Each quote can have multiple tags (like "life", "inspirational").
            # We grab all of them and join them into one text separated by commas.
            tag_elements = quote.find_all('a', class_='tag')
            tags = ', '.join(tag.text.strip() for tag in tag_elements)

            # Save this quote's info as one row of data
            news_articles.append({
                'Title': title,
                'Author': author,
                'Tags': tags,
                'Link': link,
                'Source': 'quotes.toscrape.com',
                'Date': datetime.now().strftime('%Y-%m-%d')
            })

        # ------------------------------------------------
        # Save everything we collected into a CSV file
        # ------------------------------------------------
        filename = 'news_headlines.csv'
        # encoding='utf-8-sig' makes sure special characters show up correctly in Excel
        with open(filename, 'w', newline='', encoding='utf-8-sig') as file:
            fieldnames = ['Title', 'Author', 'Tags', 'Link', 'Source', 'Date']
            writer = csv.DictWriter(file, fieldnames=fieldnames)
            writer.writeheader()        # write the column names first
            writer.writerows(news_articles)  # write all our rows of data

        print(f"✅ Successfully scraped {len(news_articles)} items!")
        print(f"📁 Data saved to {filename}")

        # Print the first 3 items so we can quickly check the data looks right
        if news_articles:
            print("\n📰 Top Items:")
            for i, article in enumerate(news_articles[:3], 1):
                print(f"{i}. \"{article['Title']}\" — {article['Author']}")

        return news_articles

    # If the site takes too long to respond (more than our 10 second timeout)
    except requests.exceptions.Timeout:
        print("❌ Connection timed out. The website might be slow.")
        return []
    # If anything else goes wrong with the request (no internet, site down, etc.)
    except requests.exceptions.RequestException as e:
        print(f"❌ Error fetching website: {e}")
        return []
    # Catch-all in case something unexpected happens, so the program doesn't just crash
    except Exception as e:
        print(f"❌ An unexpected error occurred: {e}")
        return []


def save_news_to_json(news_data):
    """Bonus: Save our scraped data to a JSON file too"""

    # If scrape_news() returned an empty list, there's nothing to save
    if not news_data:
        print("No data to save to JSON")
        return

    filename = 'news_headlines.json'
    with open(filename, 'w', encoding='utf-8') as file:
        # indent=2 makes the JSON file nicely formatted and readable
        # ensure_ascii=False keeps special characters readable instead of turning them into \u codes
        json.dump(news_data, file, indent=2, ensure_ascii=False)

    print(f"📁 Also saved to {filename} (JSON format)")


# This block only runs when we run this file directly (not when imported elsewhere)
if __name__ == "__main__":
    print("📰 'News Feed' Style Scraper (quotes.toscrape.com)")
    print("=" * 40)

    news_data = scrape_news()  # run our main scraping function

    if news_data:
        save_news_to_json(news_data)  # also save a JSON copy if we got data

    print("\n✨ Scraping complete!")
    print("Check the CSV and JSON files for the data.")
