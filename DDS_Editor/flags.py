import os
from PIL import Image


def resize_and_save(input_dir, medium_dir, small_dir):
    # Ensure output directories exist
    os.makedirs(medium_dir, exist_ok=True)
    os.makedirs(small_dir, exist_ok=True)

    # Get list of all .tga files in the input directory
    tga_files = [f for f in os.listdir(input_dir) if f.endswith('.tga')]

    # Process each image
    for file_name in tga_files:
        # Open the image
        img = Image.open(os.path.join(input_dir, file_name))

        medium_img = img.resize((41, 26))
        medium_img.save(os.path.join(medium_dir, file_name))

        small_img = img.resize((10, 7))
        small_img.save(os.path.join(small_dir, file_name))


    print(f"Total number of processed images: {len(tga_files)}")


if __name__ == "__main__":
    input_directory = 'flags'
    medium_directory = 'flags/medium'
    small_directory = 'flags/small'

    resize_and_save(input_directory, medium_directory, small_directory)
