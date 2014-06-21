# mm48

A Clojure library to provide functions for playing 2048

## Usage

### Starting Board

```clojure
(starting-board)
```

### Moving

To move a board around.

```clojure
(-> (starting-board)
    (move-up)
    (move-right)
    (move-down)
    (move-left))
```

### Move?

Given a board, will return true or false depending on if a move is possible.

```clojure
(move? (starting-board))
```

## License

Copyright © 2014 Stephen Mizell

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
