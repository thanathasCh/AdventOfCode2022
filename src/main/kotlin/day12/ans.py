import collections

grid = list(map(lambda x: x.strip("\n"), open('input.dat', 'r').readlines()))
dirs, n, m = [(0, 1), (1, 0), (-1, 0), (0, -1)], len(grid), len(grid[0])


def height(x, y): return ord('z') - ord('a') if grid[x][y] == "E" else ord(grid[x][y]) - ord('a')


def solve(start):
    visited, queue = {start}, collections.deque([(start, 0, 0)])
    while queue:
        (x, y), h, steps = queue.popleft()
        if grid[x][y] == "E": return steps
        for dx, dy in dirs:
            nx, ny = x + dx, y + dy
            if 0 <= nx < n and 0 <= ny < m and (nx, ny) not in visited and height(nx, ny) <= h + 1:
                visited.add((nx, ny))
                queue.append(((nx, ny), height(nx, ny), steps + 1))
    return None


print(min(map(lambda x: solve(x) or float('inf'),
              [(x, y) for x in range(len(grid)) for y in range(len(grid[0])) if grid[x][y] == 'S'])))  # 1

print(min(map(lambda x: solve(x) or float('inf'),
              [(x, y) for x in range(len(grid)) for y in range(len(grid[0])) if grid[x][y] == 'a'])))  # 2