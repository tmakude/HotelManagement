import React,{useState} from "react";

const HomePage=()=>{

    return(

<div className="home">
{/* HEADER / BANNER ROOM SECTION */}
 
<section>
  <header className="header-banner">
< img src="./assets/images/hotel.jpg" alt="Royal Hotel" className="header-image" />
<div className="overlay"></div>
<div className="animated-texts overlay-content">
<h1>
     Welcome to <span className="Royal-color">Royal Hotel</span>
</h1><br />
 <h3>"Luxury Stays, Exceptional Service, Unforgettable Experiences Await You"</h3>
</div>
</header>
</section>

{/* SEARCH/FIND AVAILABLE ROOM SECTION */}

<h4><a className="view-rooms-home" href="/rooms">All Rooms</a></h4>
<h2 className="home-services">Services at <span className="Royal-color">Royal Hotel</span></h2>

{/* SERVICES SECTION */}

<section className="service-section"><div className="service-card">
    
<img src="./assets/images/air-conditioner_16942171.png" alt="Air Conditioning" class="icon"/>
<div className="service-details">
<h3 className="service-title">Air Conditioning</h3>
<p className="service-description">Stay cool and comfortable throughout your stay with our indival</p>
</div>
</div>
<div className="service-card">
<img src="./assets/images/bar.png" alt="Mini Bar" />
<div className="service-details">
<h3 className="service-title">Mini Bar</h3>
<p className="service-description">Enjoy a convenient selection of beverages and snacks stock</p>
</div>
</div>

<div className="service-card">
<img src="./assets/images/parking.png" alt="Parking" />
<div className="service-details">
<h3 className="service-title">Parking</h3>
<p className="service-description">We offer on-site parking for your convenience </p>
</div>
</div>

<div className="service-card">
<img src="./assets/images/wifi.png" alt="WiFi" />
<div className="service-details">
<h3 className="service-title">WiFi</h3>
<p className="service-description">Stay connected throughout your stay </p>
</div>
</div>
</section>
</div>

    );
}
export default HomePage;