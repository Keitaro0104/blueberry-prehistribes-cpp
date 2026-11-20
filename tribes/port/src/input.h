// input.h — keyboard to MIDP key-code mapping for the Windows shell.
//
// Own implementation (not copied from any other port). The original game
// is driven by a phone keypad: D-pad (getGameAction), number keys (2/8/4/6
// move, 5 fire, 1/3/7/9 switch groups, 0 group 0), softkeys -6/-7, and
// FIRE (-5). The shell maps a PC keyboard onto that keypad:
//   arrows or WASD ......... D-pad (UP/DOWN/LEFT/RIGHT)
//   Enter, Space, or 5 ..... FIRE (-5)
//   digits 0-9 ............. the digit key itself (the game reads the
//                            ASCII code, e.g. '5' == 53 for FIRE, '0' ==
//                            48 for group 0, 1/3/7/9 for group switching)
//   F1 or Q ................ left softkey (-6)
//   Esc or F2 .............. right softkey (-7, back/cancel in menus)

#pragma once

// Map a Win32 virtual-key code to a MIDP key code. Returns false when the
// key is not a game key.
bool winKeyToMidp(int vk, int& outCode);

// Forward a key press/release to the current canvas (the game's
// keyPressed/keyReleased). Called from the window procedure.
void winForwardKey(int midpCode, bool pressed);
