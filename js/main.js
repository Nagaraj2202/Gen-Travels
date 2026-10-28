document.addEventListener('DOMContentLoaded', () => {
    const swipeBtn = document.getElementById('swipeBtn');
    const swipeDownBtn = document.getElementById('swipeDownBtn');
    const exploreSection = document.getElementById('exploreSection');
    const swipeArea = document.getElementById('swipeArea');
  
    // Swipe Up Trigger
    swipeBtn.addEventListener('click', () => {
      exploreSection.classList.remove('hidden');
      exploreSection.classList.add('visible');
      exploreSection.scrollIntoView({ behavior: 'smooth' });
    });
  
    // Swipe Down Trigger
    if (swipeDownBtn) {
      swipeDownBtn.addEventListener('click', () => {
        swipeArea.scrollIntoView({ behavior: 'smooth' });
        setTimeout(() => {
          exploreSection.classList.remove('visible');
          exploreSection.classList.add('hidden');
        }, 500);
      });
    }
  
    // Touch Gesture Detection for Mobile Swipe
    let startY = 0;
    window.addEventListener('touchstart', (e) => {
      startY = e.touches[0].clientY;
    });
  
    window.addEventListener('touchend', (e) => {
      let endY = e.changedTouches[0].clientY;
      if (startY - endY > 80) {
        // Swiped Up
        exploreSection.classList.remove('hidden');
        exploreSection.classList.add('visible');
      } else if (endY - startY > 80) {
        // Swiped Down
        swipeArea.scrollIntoView({ behavior: 'smooth' });
      }
    });
  });