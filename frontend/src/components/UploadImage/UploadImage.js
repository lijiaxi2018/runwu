import React, { useState } from "react";
import Axios from 'axios';

function UploadImage() {
    const clientUpload = async () => {
        var formData = new FormData();
        formData.append('file', image);
        
        const response = await Axios.post(`http://localhost:8081/api/Account/Upload`, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
          })

        console.log(response)
        console.log(response.data)
    };

    const [image, setImage] = useState(null);

    function handleSelect(event) {
        console.log(event.target.files[0].files);
        setImage(event.target.files[0]);
    }

    return (
        <div>
            {image &&
                <div>
                    <img alt="selected" width={"250px"} src={URL.createObjectURL(image)} />
                </div>
            }
            <h1>Please upload your image</h1>
            <input type="file" accept="image/jpeg, image/png" name="avatar" onChange={handleSelect}/>
            <button type="submit" onClick={clientUpload}>Upload</button>
        </div>
    );
}

export default UploadImage;