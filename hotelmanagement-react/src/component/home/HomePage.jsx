import React from "react";

const HomePage=()=>{

    return(

<div className="home">
{/* HEADER / BANNER ROOM SECTION */}
 
<section>
  <header className="header-banner">
< img src="/assets/images/hotel.jpg" alt="Royal Hotel" className="header-image" />
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
    
<img src="/assests/images/air-conditioner_16942171.png" alt="Air Conditioning" />
<div className="service-details">
<h3 className="service-title">Air Conditioning</h3>
<p className="service-description">Experience ultimate comfort with our efficient air conditioning, tailored to your preferred temperature.</p>
</div>
</div>
<div className="service-card">
<img src="/assests/images/bar.png" alt="Mini Bar" />
<div className="service-details">
<h3 className="service-title">Mini Bar</h3>
<p className="service-description">Enjoy a selection of beverages and snacks in your room with our stocked mini bar.</p>
</div>
</div>

<div className="service-card">
<img src="/assests/images/parking.png" alt="Parking"  />
<div className="service-details">
<h3 className="service-title">Parking</h3>
<p className="service-description">Convenient on-site parking available for all guests. </p>
</div>
</div>

<div className="service-card">
<img src="./assests/images/WIFI.jpg" alt="WiFi" />
<div className="service-details">
<h3 className="service-title">WiFi</h3>
<p className="service-description">Stay cool with our easily adjustable air conditioning for your comfort.</p>
</div>
</div>
</section>
</div>

    );
}
export default HomePage;