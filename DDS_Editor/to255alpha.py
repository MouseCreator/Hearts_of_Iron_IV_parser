from PIL import Image
import numpy as np

image_name = "in_image.dds"
out_name = "out.dds"
def process_image(in_img):
    img = Image.open(in_img).convert("RGBA")
    width, height = img.size
    pixels = np.array(img)

    with open("alphas.txt", "w") as f:
        for y in range(height):
            for x in range(width):
                alpha = pixels[y, x, 3]
                f.write(f"{x} {y} {alpha}\n")
                pixels[y, x, 3] = 255

    out_img = Image.fromarray(pixels, "RGBA")
    out_img.save(out_name, format="DDS")


if __name__ == "__main__":
    process_image(image_name)
    print("TO255 is completed")