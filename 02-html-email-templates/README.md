# HTML Email Templates

This project contains responsive HTML email templates built with inline CSS and table-based layouts — the standard approach for email development, since most email clients (Outlook, Gmail, etc.) don't support modern CSS like Flexbox or Grid.

## Templates

### [newsletter.html](./newsletter.html)
A monthly newsletter layout with a header, a two-column feature section, a call-to-action button, and a footer.

### [welcome_template.html](./welcome_template.html)
A welcome/onboarding email with a gradient header, a feature highlights list, a call-to-action button, and a footer with social links and a physical address.

## What I Learned

- **Table-based layouts** — using nested `<table>` elements instead of `<div>`/Flexbox, since email clients render HTML very differently from web browsers
- **Inline CSS** — styles must be written inline (`style="..."`) because most email clients strip out `<style>` blocks or external stylesheets
- **`role="presentation"`** — added to layout tables so screen readers don't misread them as data tables, improving accessibility
- **MSO conditional comments** (`<!--[if mso]>...<![endif]-->`) — used to fix rendering issues specific to Outlook, which uses Microsoft Word's engine to render emails instead of a standard browser engine
- **Preheader text** — a hidden snippet of text that shows up as the preview next to the subject line in an inbox, added using `display:none` combined with `mso-hide:all`
- **`color-scheme` meta tags** — added for better support in email clients with dark mode

## Notes

- Links in these templates use placeholder `href="#"` values since this is a portfolio demo, not a live campaign.
- No external images are used; all visuals are built with CSS (gradients, colors, emoji icons) to keep the templates self-contained and easy to preview.

## How to Preview

Simply open either `.html` file directly in a web browser to see the layout. For a true accuracy check across email clients, tools like [Litmus](https://www.litmus.com/) or [Email on Acid](https://www.emailonacid.com/) are commonly used in real projects.
