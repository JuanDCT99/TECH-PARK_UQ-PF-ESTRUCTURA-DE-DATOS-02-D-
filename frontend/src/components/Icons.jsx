import React from 'react';

export const VisitanteIcon = ({ size = 48 }) => (
  <svg width={size} height={size} viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg">
    <circle cx="24" cy="12" r="6" fill="#D07070" stroke="#B06060" strokeWidth="1.5"/>
    <path d="M12 46c0-10 5.4-16 12-16s12 6 12 16" fill="#E8A0A0" stroke="#B06060" strokeWidth="1.5"/>
    <path d="M24 30L20 22h8l-4 8z" fill="#D07070" stroke="#B06060" strokeWidth="1.2"/>
    <circle cx="20" cy="18" r="2" fill="#fff" opacity="0.6"/>
    <circle cx="28" cy="18" r="2" fill="#fff" opacity="0.6"/>
    <line x1="8" y1="26" x2="18" y2="22" stroke="#D07070" strokeWidth="1.5" strokeLinecap="round"/>
    <line x1="40" y1="26" x2="30" y2="22" stroke="#D07070" strokeWidth="1.5" strokeLinecap="round"/>
  </svg>
);

export const EmpleadoIcon = ({ size = 48 }) => (
  <svg width={size} height={size} viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg">
    <rect x="14" y="10" width="20" height="26" rx="3" fill="#D4E8F0" stroke="#8AB4C8" strokeWidth="1.5"/>
    <rect x="18" y="14" width="12" height="4" rx="1" fill="#8AB4C8"/>
    <rect x="18" y="20" width="8" height="3" rx="1" fill="#8AB4C8"/>
    <rect x="18" y="26" width="10" height="3" rx="1" fill="#8AB4C8"/>
    <circle cx="33" cy="20" r="3" fill="#FFD89A" stroke="#D4A060" strokeWidth="1.2"/>
    <line x1="33" y1="18" x2="33" y2="22" stroke="#D4A060" strokeWidth="1.5" strokeLinecap="round"/>
    <line x1="31" y1="20" x2="35" y2="20" stroke="#D4A060" strokeWidth="1.5" strokeLinecap="round"/>
    <path d="M36 28l4 3-2 5" stroke="#8AB4C8" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round"/>
  </svg>
);

export const AdminIcon = ({ size = 48 }) => (
  <svg width={size} height={size} viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path d="M24 6l4 8 9-2-5 8 7 6-8 4 2 9-9-3-9 3 2-9-8-4 7-6-5-8 9 2 4-8z" fill="#FFE4B5" stroke="#D4A060" strokeWidth="1.5" strokeLinejoin="round"/>
    <circle cx="24" cy="24" r="6" fill="#D4A060" opacity="0.3"/>
    <path d="M24 20v8M20 24h8" stroke="#D4A060" strokeWidth="2" strokeLinecap="round"/>
  </svg>
);
