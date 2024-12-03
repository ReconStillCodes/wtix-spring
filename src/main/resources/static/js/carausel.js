const prevBtn = document.getElementById("btn-prv");
const nextBtn = document.getElementById("btn-nxt");
var carauselImages = document.getElementById('carausel-images');
var images = [];
var movies = [];

const baseURL = "https://localhost:8080"

class Image {
  constructor(pos, img) {
    this.pos = pos;
    this.img = img;
  }
}

window.onload = async () => {
//    fetchCarauselMovies();
//
//    console.log(movies);

    for (var i = 1; i <= 6; i++) {
        const img = document.getElementById(`d${i}`);
        var pos = (i - 2) * 40;
        if (pos < 0 || pos > 120) {
            img.style.opacity = `0%`;
        }
        var image = new Image(pos, img);
        console.log(image);
        images.push(image);
    }
};

function updateCarausel(multiplier) {
  for (var i = 1; i <= 6; i++) {
    var img = images[i - 1].img;
    img.style.opacity = `100%`;
    var oldPos = images[i - 1].pos;
    var newPos = oldPos + 40 * multiplier;

    if (newPos > 160 && multiplier > 0) {
      newPos = -40;
      img.style.opacity = `0%`;
    }

    if (newPos < -40 && multiplier < 0) {
      newPos = 160;
      img.style.opacity = `0%`;
    }

    img.style.transform = `translateX(${newPos}vh)`;

    images[i - 1].pos = newPos;
    images[i - 1].img = img;
  }
}
