import {
  Component,
  Input,
  ViewChild,
  ElementRef,
  AfterViewInit,
} from '@angular/core';
import {
  Storage,
  ref,
  uploadBytes,
  listAll,
  getDownloadURL,
} from '@angular/fire/storage';

@Component({
  selector: 'app-food-image',
  templateUrl: './food-image.component.html',
  styleUrls: ['./food-image.component.scss'],
})
export class FoodImageComponent implements AfterViewInit {
  @ViewChild('imageInput') imageInput!:
    | ElementRef<HTMLInputElement>
    | undefined;
  @Input() imageUrl: string | undefined;
  @Input() editMode: boolean = false;

  selectedFile: File | undefined;
  storage: Storage;

  constructor(storage: Storage) {
    this.storage = storage;
  }

  ngAfterViewInit() {
    // Listen to the 'change' event of the image input
    if (this.imageInput) {
      this.imageInput.nativeElement.addEventListener(
        'change',
        this.handleFileInput.bind(this)
      );
    }
  }

  handleFileInput(event: Event) {
    // Handles the selection of an image file
    const file = (event.target as HTMLInputElement).files?.[0];

    if (file) {
      this.selectedFile = file;
      this.uploadImage(file);
    }
  }

  uploadImage(file: File) {
    // Uploads the image to Firebase Storage
    const imgRef = ref(this.storage, `images/${file.name}`);

    uploadBytes(imgRef, file)
      .then((response) => {
        // Image uploaded successfully
        this.getImageUrl(imgRef);
      })
      .catch((error) => console.log(error));
  }

  getImageUrl(ref: any) {
    // Gets the download URL of the uploaded image
    getDownloadURL(ref)
      .then((url) => {
        // Image URL obtained successfully
        this.imageUrl = url;
      })
      .catch((error) => console.log(error));
  }

  openFileExplorer(event: Event) {
    if (this.editMode) {
      // Opens the file explorer when an element is clicked
      event.preventDefault();
      if (this.imageInput) {
        this.imageInput.nativeElement.click();
      }
    }
  }
}
