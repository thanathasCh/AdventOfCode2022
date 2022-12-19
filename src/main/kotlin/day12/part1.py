def findS():
    for i, v in enumerate(map):
        if 'S' in v:
            index = v.index('S')
            v[index] = 'a'
            return [i, index]


def findE():
    for i, v in enumerate(map):
        if 'E' in v:
            index = v.index('E')
            v[index] = 'z'
            return [i, index]


directions = [
    [0, 1],
    [-1, 0],
    [1, 0],
    [0, -1]
]

map = [list(x) for x in open('test.dat', 'r').read().split('\n')]
h, w = len(map), len(map[0])
visited = [[False for y in range(w)] for x in range(h)]
start = findS()
end = findE()
queues = [start + [0]]

while len(queues) > 0:
    state = queues.pop(0)
    for dir in directions:
        newState = [state[0] + dir[0], state[1] + dir[1], state[2] + 1]

        if newState[:2] == end:
            print(f'ans part1 {newState[2]}')
            break

        if newState[0] in range(h) and newState[1] in range(w) and ord(map[newState[0]][newState[1]]) - ord(
                map[state[0]][state[1]]) <= 1 and not visited[newState[0]][newState[1]]:
            queues.append(newState)
            visited[newState[0]][newState[1]] = True
