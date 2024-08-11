from PIL import Image
import numpy as np

alphas_name = "alphas.txt"
image_name = "out.dds"
output_name = "modified_out.dds"
def modify_image(alphas_file, dds_file):
    img = Image.open(dds_file).convert("RGBA")
    pixels = np.array(img)

    with open(alphas_file, "r") as f:
        for line in f:
            x, y, alpha = map(int, line.strip().split())
            pixels[y, x, 3] = alpha

    modified_img = Image.fromarray(pixels, "RGBA")
    modified_img.save(output_name, format="DDS")


if __name__ == "__main__":
    modify_image(alphas_name, image_name)
    print("TO1 is completed")