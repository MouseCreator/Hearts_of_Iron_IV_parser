from PIL import Image
import os

frame_path = "portraits/base/Minister Base Alternate.png"
input_dir = "portraits/input"


def exect():
    frame = Image.open(frame_path).convert("RGBA")

    input_files = [f for f in os.listdir(input_dir) if f.endswith('.dds')]
    for input_file in input_files:
        output_dir = "portraits/output"
        portrait_path = os.path.join(input_dir, input_file)
        if not os.path.exists(output_dir):
            os.makedirs(output_dir)
        output_path = os.path.join(output_dir, "idea_" + input_file)

        portrait = Image.open(portrait_path).convert("RGBA")

        portrait_resized = portrait.resize((36, 49))

        portrait_rotated = portrait_resized.rotate(5, expand=True)

        background = Image.new("RGBA", frame.size, (0, 0, 0, 0))

        background.paste(portrait_rotated, (4, 5), portrait_rotated)

        combined = Image.alpha_composite(background, frame)

        combined.save(output_path, "dds")


if __name__ == "__main__":
    exect()
