#!/usr/bin/env python3

from PIL import Image, ImageDraw, ImageFont

def create_placeholder(size, text):
    """
    Creates a placeholder image with specified dimensions and text.

    :param size: Tuple (width, height) for the image size.
    :param text: Text to display on the placeholder image.
    """
    width, height = size
    output_file = f"{text.lower().replace(' ', '_')}_{width}x{height}.png"

    # Create an image with a gray background
    image = Image.new("RGB", size, "gray")
    draw = ImageDraw.Draw(image)
    font = ImageFont.load_default()

    # Calculate text position to center it
    text_size = draw.textsize(text, font=font)
    text_position = ((width - text_size[0]) // 2, (height - text_size[1]) // 2)

    # Draw text
    draw.text(text_position, text, fill="white", font=font)

    # Save the image
    image.save(output_file)
    print(f"Created {output_file}")

# Example usage with simple array of data
assets = [
    ("I-mdpi", 36,36),
    ("I-hdpi", 48,48),
    ("I-xhdpi", 64,64),
    ("I-xxhdpi", 96,96),
    ("I-xxxhdpi", 128,128),

    ("App Icon", 512, 512),
    ("Feature Graphic", 1024, 500),
    ("Phone Screenshots 1", 1080, 1920),
    ("Phone Screenshots 2", 1080, 1920),
    ("Phone Screenshots 3", 1080, 1920),
    ("Phone Screenshots 4", 1080, 1920),
    ("7-inch Tablet Screenshots 1", 1080, 1920),
    ("7-inch Tablet Screenshots 2", 1080, 1920),
    ("7-inch Tablet Screenshots 3", 1080, 1920),
    ("7-inch Tablet Screenshots 4", 1080, 1920),
    ("10-inch Tablet Screenshots 1", 1080, 1920),
    ("10-inch Tablet Screenshots 2", 1080, 1920),
    ("10-inch Tablet Screenshots 3", 1080, 1920),
    ("10-inch Tablet Screenshots 4", 1080, 1920),
    ("Wear OS Screenshot 1", 384, 384),
    ("Wear OS Screenshot 2", 384, 384),
    ("Wear OS Screenshot 3", 384, 384),
    ("Wear OS Screenshot 4", 384, 384),
    ("Chromebook Screenshot 1", 1080, 1920),
    ("Chromebook Screenshot 2", 1080, 1920),
    ("Chromebook Screenshot 3", 1080, 1920),
    ("Chromebook Screenshot 4", 1080, 1920)
]

for name, width, height in assets:
    create_placeholder((width, height), name)
