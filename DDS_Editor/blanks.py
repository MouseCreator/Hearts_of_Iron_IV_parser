import os
from PIL import Image

prefix = "ENN"
target_count = 3
at = "gfx\leaders\\" + prefix


def create_template_files(prefix, target_count):
    with open("blanks/dir.txt", "r") as f:
        directory = f.readline().strip()

    if not os.path.exists(directory):
        os.makedirs(directory)

    directory = directory + at

    for i in range(1, target_count + 1):
        file_name = f"{prefix}_char{i}.dds"
        file_path = os.path.join(directory, file_name)

        if os.path.exists(file_path):
            print(f"File {file_name} already exists. Skipping...")
            continue

        img = Image.new("RGBA", (156, 210), (0, 0, 0, 0))

        img.save(file_path)
        print(f"Created {file_name} at {directory}")


if __name__ == "__main__":
    create_template_files(prefix, target_count)
